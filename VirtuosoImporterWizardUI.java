package org.ualberta.virtuosoimporter;

import org.gephi.io.importer.spi.Importer;
import org.gephi.io.importer.spi.ImporterWizardUI;
import org.gephi.io.importer.spi.SpigotImporter;
import org.openide.WizardDescriptor.Panel;
import org.openide.util.lookup.ServiceProvider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew O
 */
@ServiceProvider(service = ImporterWizardUI.class)
public class VirtuosoImporterWizardUI implements ImporterWizardUI {
    private Panel[] panels = null;
    
     @Override
    public String getDisplayName() {
        return "Virtuoso Importer";
    }
     
     @Override
    public String getCategory() {
        return "RDF";
    }
     
     @Override
    public String getDescription() {
        return "Imports RDF data from a Virtuoso server";
    }
     
     @Override
    public Panel[] getPanels() {
         if (panels == null || panels.length == 0) {
            panels = new Panel[1];
            panels[0] = new VirtuosoImporterPanel1();
        }
        return panels;
    }
     
     @Override
    public void setup(Panel panel) {
        ((VirtuosoImporterPanel1) ((Panel) panels[0]).getComponent()).setup();
    }
     
      @Override
    public void unsetup(SpigotImporter importer, Panel panel) {
        //When the wizard has been closed 
          
        ((VirtuosoImporterPanel1) ((Panel) panels[0]).getComponent()).unsetup((VirtuosoImporter)importer);
 
        panels = null;
    }
      
      @Override
    public boolean isUIForImporter(Importer importer) {
        return importer instanceof VirtuosoImporter;
    }
}