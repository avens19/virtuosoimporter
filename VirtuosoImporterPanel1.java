/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ualberta.virtuosoimporter;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 *
 * @author Andrew O
 */
public class VirtuosoImporterPanel1 extends javax.swing.JPanel implements WizardDescriptor.Panel {

    private Component component;
    private List<ChangeListener> listeners;

    /**
     * Creates new form VirtuosoImporterPanel1
     */
    public VirtuosoImporterPanel1() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        p_host = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        p_graph = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        p_username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        p_subject = new javax.swing.JTextField();
        p_password = new javax.swing.JPasswordField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.jLabel1.text")); // NOI18N

        p_host.setText(org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.p_host.text")); // NOI18N
        p_host.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_usernameKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.jLabel2.text")); // NOI18N

        p_graph.setText(org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.p_graph.text")); // NOI18N
        p_graph.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_usernameKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.jLabel3.text")); // NOI18N

        p_username.setText(org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.p_username.text")); // NOI18N
        p_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_usernameKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.jLabel5.text")); // NOI18N

        p_subject.setText(org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.p_subject.text")); // NOI18N
        p_subject.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_usernameKeyTyped(evt);
            }
        });

        p_password.setText(org.openide.util.NbBundle.getMessage(VirtuosoImporterPanel1.class, "VirtuosoImporterPanel1.p_password.text")); // NOI18N
        p_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_usernameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p_username, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p_graph)
                    .addComponent(p_host)
                    .addComponent(p_subject)
                    .addComponent(p_password, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(p_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(p_graph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(p_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(p_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(p_subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void p_usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_usernameKeyTyped
        tell();
    }//GEN-LAST:event_p_usernameKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField p_graph;
    private javax.swing.JTextField p_host;
    private javax.swing.JPasswordField p_password;
    private javax.swing.JTextField p_subject;
    private javax.swing.JTextField p_username;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public org.openide.util.HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public void readSettings(Object data) {
    }

    @Override
    public void storeSettings(Object data) {
    }

    @Override
    public void addChangeListener(ChangeListener cl) {
        if (listeners == null) {
            listeners = new ArrayList();
        }

        listeners.add(cl);
    }

    @Override
    public void removeChangeListener(ChangeListener cl) {
        listeners.remove(cl);
    }

    @Override
    public boolean isValid() {
        try {
            return !(p_host.getText().isEmpty() || p_graph.getText().isEmpty() || p_username.getText().isEmpty() || p_password.getPassword().length == 0 || p_subject.getText().isEmpty());
        } catch (Exception e) {
        }
        return false;
    }

    public void tell() {
        if (listeners != null) {
            for (ChangeListener cl : listeners) {
                cl.stateChanged(null);
            }
        }
    }

    public void unsetup(VirtuosoImporter importer) {
        importer.hosturl = p_host.getText();
        importer.graphname = p_graph.getText();
        importer.username = p_username.getText();
        importer.password = String.valueOf(p_password.getPassword());
        importer.subject = p_subject.getText();
    }

    public void setup() {
        p_host.setText("jdbc:virtuoso://localhost:1111");
        p_username.setText("dba");
        p_password.setText("");
    }
}