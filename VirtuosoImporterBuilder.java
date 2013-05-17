package org.ualberta.virtuosoimporter;

import org.gephi.io.importer.spi.Importer;
import org.gephi.io.importer.spi.ImporterBuilder;
import org.gephi.io.importer.spi.SpigotImporter;
import org.gephi.io.importer.spi.SpigotImporterBuilder;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew O
 */

@ServiceProvider(service = SpigotImporterBuilder.class)
public class VirtuosoImporterBuilder implements SpigotImporterBuilder{

    @Override
    public SpigotImporter buildImporter() {
        return new VirtuosoImporter();
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(VirtuosoImporterBuilder.class, "VirtuosoImporterBuilder.name");
    }
    
}
