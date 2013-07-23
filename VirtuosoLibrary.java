/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ualberta.virtuosoimporter;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.graph.impl.LiteralLabel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryException;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;
import virtuoso.jena.driver.VirtuosoUpdateFactory;
import virtuoso.jena.driver.VirtuosoUpdateRequest;

/**
 * A library for accessing and manipulating RDF data via a Virtuoso server
 *
 * @author Andrew O
 */
public class VirtuosoLibrary {

    /**
     * Used to describe the type of data being stored as an object
     */
    public enum ObjectType {

        URI, String, Integer, Double, Long
    }

    /**
     * Connects to the specified Virtuoso graph
     *
     * @param host The URL of the Virtuoso instance on the jdbc port
     * @param graphName The name of the graph you wish to connect to (does not
     * have to exist, this will perform creation)
     * @param username The user account which will be used for authentication
     * @param password The password that will be used for authentication
     * @return An open connection to the graph
     */
    public static VirtGraph connect(String host, String graphName, String username, String password) {
        return new VirtGraph(graphName, host, username, password);
    }

    /**
     * Finds all triples in the current graph where the specified URI is the
     * subject
     *
     * @param graph The graph to search
     * @param subjectURI The URI to search for
     * @return A list of all triples in the graph with the given subject
     */
    public static List findAllTriplesForSubject(VirtGraph graph, String subjectURI) {
        Node subject = Node.createURI(subjectURI);

        ExtendedIterator ei = graph.find(subject, Node.ANY, Node.ANY);

        return ei.toList();
    }
    
    /**
     * Finds all triples in the current graph where the specified URI is the
     * subject, and recursively find triples for a given number of levels
     *
     * @param graph The graph to search
     * @param subjectURI The URI to search for
     * @param numLevels The number of edges to traverse to find new subjects (i.e.: 0 gets only the subject and it's triples)
     * @return A list of all triples in the graph with the given subject
     */
    public static List findAllTriplesForSubjectRecursively(VirtGraph graph, String subjectURI, int numLevels) {
        HashSet complete = new HashSet();
        List<Triple> finalSet = new ArrayList();
        List<Triple> currentSet;
        
        Node subject = Node.createURI(subjectURI);
        
        complete.add(subjectURI);

        ExtendedIterator ei = graph.find(subject, Node.ANY, Node.ANY);
        
        currentSet = ei.toList();
        
        finalSet.addAll(currentSet);
        
        for(int i = 0;i < numLevels;i++)
        {
            List<Triple> nextSet = new ArrayList();
            for(Triple t : currentSet)
            {
                Node object = t.getObject();
                
                if(object.isURI() && !complete.contains(object.toString()))
                {
                    complete.add(object.toString());
                    List<Triple> result = graph.find(object, Node.ANY, Node.ANY).toList();
                    
                    nextSet.addAll(result);
                    finalSet.addAll(result);
                }
            }
            
            currentSet = nextSet;
        }

        return finalSet;
    }

    /**
     * Updates the given triple with the new data
     *
     * Note: the new data must be the same data type as the old data Note: this
     * will save the new triple in the graph
     *
     * @param graph The graph where the triple originated
     * @param oldTriple The triple to be edited
     * @param newData The new object data
     * @return The new triple
     */
    public static Triple updateObject(VirtGraph graph, Triple oldTriple, String newData) {
        Triple newTrip = null;

        if (oldTriple.getObject().isLiteral()) {
            newTrip = new Triple(oldTriple.getSubject(), oldTriple.getPredicate(), Node.createLiteral(newData, "", oldTriple.getObject().getLiteralDatatype()));
        } else {
            newTrip = new Triple(oldTriple.getSubject(), oldTriple.getPredicate(), Node.createURI(newData));
        }

        graph.add(newTrip);

        graph.delete(oldTriple);

        return newTrip;
    }

    /**
     * Adds a new triple to the graph
     *
     * @param graph The graph to insert into
     * @param subject The URI of the subject
     * @param predicate The URI of the predicate
     * @param object The object data
     * @param objecttype The data type of the object
     * @return The new triple
     */
    public static Triple addTriple(VirtGraph graph, String subject, String predicate, String object, ObjectType objecttype) {
        Node sub = Node.createURI(subject);
        Node pred = Node.createURI(predicate);
        Node obj = createObject(object, objecttype);

        Triple newTrip = new Triple(sub, pred, obj);
        graph.add(newTrip);

        return newTrip;
    }

    /**
     * Removes the given triple from the graph
     *
     * @param graph The graph to delete from
     * @param t The triple to remove
     */
    public static void deleteTriple(VirtGraph graph, Triple t) {
        graph.delete(t);
    }

    /**
     * Adds all triples from the given .ntriples file into the graph
     *
     * @param graph The graph to import into
     * @param file The .ntriples file to import
     * @throws IOException Will throw if an IO error occurs
     */
    public static void importFromNTriplesFile(VirtGraph graph, File file) throws IOException {

        BufferedReader fr = new BufferedReader(new FileReader(file.getAbsolutePath()));
        String line = fr.readLine();
        while (line != null) {
            try {
                String str = "INSERT INTO GRAPH <" + graph.getGraphName() + "> { " + line + " }";
                VirtuosoUpdateRequest vur = VirtuosoUpdateFactory.create(str, graph);
                vur.exec();
            } catch (Exception e) {
                System.out.println("Error on line: " + line);
            }

            line = fr.readLine();
        }
    }

    /**
     * Submits a SPARQL query on the given graph
     *
     * @param graph The graph to query
     * @param queryText The SPARQL query
     * @return The results of the query
     * @throws QueryException Will throw for SPARQL syntax errors
     */
    public static ResultSet queryGraph(VirtGraph graph, String queryText) throws QueryException {
        Query sparql = QueryFactory.create(queryText);

        VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(sparql, graph);

        return vqe.execSelect();
    }

    /**
     * Checks if the given triple exists in the graph
     *
     * @param graph The graph to look in
     * @param subject The subject
     * @param predicate The predicate
     * @param object The object data
     * @param objecttype The object data type
     * @return Whether the triple was found
     */
    public static boolean contains(VirtGraph graph, String subject, String predicate, String object, ObjectType objecttype) {
        Node sub = Node.createURI(subject);
        Node pred = Node.createURI(predicate);
        Node obj = createObject(object, objecttype);

        return graph.find(sub, pred, obj).hasNext();
    }

    /**
     * Creates an object of the given type
     *
     * @param object The data for the object
     * @param objecttype The data type of the object
     * @return The new object
     */
    private static Node createObject(String object, ObjectType objecttype) {
        Node obj;

        switch (objecttype) {
            case URI:
                obj = Node.createURI(object);
                break;
            case String:
                obj = Node.createLiteral(object);
                break;
            case Integer:
                obj = Node.createLiteral(new LiteralLabel(Integer.parseInt(object)));
                break;
            case Double:
                obj = Node.createLiteral(new LiteralLabel(Double.parseDouble(object)));
                break;
            case Long:
                obj = Node.createLiteral(new LiteralLabel(Long.parseLong(object)));
                break;
            default:
                obj = Node.createAnon();
        }

        return obj;
    }

    /**
     * Checks if the given subject has any triples originating from it
     *
     * @param graph The graph to look in
     * @param subject The subject to inspect
     * @return Whether the subject has any edges
     */
    public static boolean hasEdges(VirtGraph graph, String subject) {
        Node sub = Node.createURI(subject);
        Node pred = Node.ANY;
        Node obj = Node.ANY;

        return graph.find(sub, pred, obj).hasNext();
    }
}
