package org.ualberta.virtuosoimporter;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryException;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.data.attributes.api.AttributeType;
import org.gephi.io.importer.api.ContainerLoader;
import org.gephi.io.importer.api.EdgeDraft;
import org.gephi.io.importer.api.Issue;
import org.gephi.io.importer.api.NodeDraft;
import org.gephi.io.importer.api.Report;
import org.gephi.io.importer.spi.Importer;
import org.gephi.io.importer.spi.SpigotImporter;
import org.gephi.utils.longtask.spi.LongTask;
import org.gephi.utils.progress.ProgressTicket;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrew O
 */
public class VirtuosoImporter implements SpigotImporter, LongTask {
    
    public String graphname = "Facebook";
    VirtGraph graph;
    public String hosturl = "jdbc:virtuoso://localhost:1111";
    public String username = "dba";
    public String password = "dba";
    public String subject = "";
    ContainerLoader container;
    Report report;
    private ProgressTicket progressTicket;
    private boolean cancel = false;
    
    @Override
    public boolean execute(ContainerLoader cl) {
        container = cl;
        report = new Report();
        graph = new VirtGraph(graphname, hosturl, username, password);
        
        Query sparql;
        
        progressTicket.start();
        
        try {
            sparql = QueryFactory.create("select * where {<"+ subject + "> ?p ?o}");
            
            AttributeModel o = container.getAttributeModel();
            AttributeColumn ac = o.getEdgeTable().getColumn("Label");
            
            VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create(sparql, graph);
            
            ResultSet results = vqe.execSelect();
            
            while (results.hasNext()) {
                QuerySolution result = results.nextSolution();
                //RDFNode sub = result.get("s");
                RDFNode pred = result.get("p");
                RDFNode obj = result.get("o");
                NodeDraft node1 = null;
                if (container.nodeExists(subject)) {
                    node1 = container.getNode(subject);
                } else {
                    node1 = container.factory().newNodeDraft();
                    node1.setId(subject);

                    //Don't forget to add the node
                    container.addNode(node1);
                }
                NodeDraft node2 = null;
                if (!obj.isLiteral()) {
                    if (container.nodeExists(obj.toString())) {
                        node2 = container.getNode(obj.toString());
                    } else {
                        node2 = container.factory().newNodeDraft();
                        node2.setId(obj.toString());

                        //Don't forget to add the node
                        container.addNode(node2);
                    }
                } else {
                    node2 = container.factory().newNodeDraft();
                    if (!container.nodeExists(obj.toString())) {
                        node2.setId(obj.toString());
                    } else {
                        int i = 2;
                        while (container.nodeExists(obj.toString()+"_"+String.valueOf(i))) {
                            i++;
                        }
                        node2.setId(obj.toString()+"_"+String.valueOf(i));
                    }
                    
                    container.addNode(node2);
                }
                
                EdgeDraft ed = container.factory().newEdgeDraft();
                ed.setLabel(pred.toString());
                ed.setSource(node1);
                ed.setTarget(node2);
                container.addEdge(ed);
            }
            
            report.log("Got " + results.getRowNumber() + " results from the query");
            
        } catch (QueryException e) {
            report.logIssue(new Issue(e.getMessage(),Issue.Level.CRITICAL,e));
        }
        
        progressTicket.finish();
        
        return !cancel;
    }
    
    @Override
    public ContainerLoader getContainer() {
        return container;
    }
    
    public boolean cancel() {
        cancel = true;
        return true;
    }
    
    public void setProgressTicket(ProgressTicket progressTicket) {
        this.progressTicket = progressTicket;
    }
    
    @Override
    public Report getReport() {
        return report;
    }
}