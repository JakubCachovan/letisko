/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import letisko.Cestujuci;
import sql.sql_connect;

/**
 *
 * @author Acer
 */
public final class RezervaciaJDialog extends javax.swing.JDialog {
    private Cestujuci cestujuci = null;
    private ResultSet rs = null; 
    private String dbPath;
    /**
     * Creates new form RezervaciaJDialog
     * @param parent
     * @param modal
     */
    public RezervaciaJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();       
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }
    
    public void fillTableCestujuci(){
        try (Connection con = sql_connect.ConnectDB(this.dbPath);){
            Statement state = con.createStatement();
            rs = state.executeQuery("SELECT * FROM cestujuci");
            DefaultTableModel m = (DefaultTableModel)jTableExistujuciCestujuci.getModel();
            while (rs.next()) {
               String [] row = {rs.getString("meno"), rs.getString("priezvisko"), rs.getString("rodne_cislo")};
               m.addRow(row);                
            }  
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }  
    
    public Cestujuci getCestujuci(){
        return cestujuci;
    }
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableExistujuciCestujuci = new javax.swing.JTable();
        jButtonNovy = new javax.swing.JButton();
        jButtonExistujuci = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rezervácia letenky");

        jTableExistujuciCestujuci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meno", "Priezvisko", "Rodné číslo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableExistujuciCestujuci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableExistujuciCestujuciMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableExistujuciCestujuci);

        jButtonNovy.setText("Nový cestujúci");
        jButtonNovy.setActionCommand("");
        jButtonNovy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovyActionPerformed(evt);
            }
        });

        jButtonExistujuci.setText("Pridaj existujúceho");
        jButtonExistujuci.setEnabled(false);
        jButtonExistujuci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExistujuciActionPerformed(evt);
            }
        });

        jLabel1.setText("Existujúci cestujúci");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNovy, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(jButtonExistujuci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonExistujuci, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovyActionPerformed
        // TODO add your handling code here:
        CestujuciJDialog cestujuciJDialog = new CestujuciJDialog(null, true);
        cestujuciJDialog.setLocationRelativeTo(null);
        cestujuciJDialog.setVisible(true);
        cestujuci = cestujuciJDialog.getCestujuci();
        if(cestujuci != null){
            try (Connection con = sql_connect.ConnectDB(this.dbPath);){ 
                Statement state = con.createStatement();
                String sql = "INSERT INTO cestujuci VALUES (\""+cestujuci.getRC()+"\", \""+cestujuci.getMeno()+"\", \""+cestujuci.getPriezvisko()+"\");";
                state.executeUpdate(sql);
                con.close();
                dispose();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Cestujúci so zadaným rodným číslom existuje !");
            }    
        }            
    }//GEN-LAST:event_jButtonNovyActionPerformed

    private void jButtonExistujuciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExistujuciActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel m = (DefaultTableModel)jTableExistujuciCestujuci.getModel();
            int selectedRow = jTableExistujuciCestujuci.getSelectedRow();   
            String rc = m.getValueAt(selectedRow, 2).toString();
            String meno = m.getValueAt(selectedRow, 0).toString();
            String priezvisko = m.getValueAt(selectedRow, 1).toString();          
            cestujuci = new Cestujuci(meno, priezvisko, rc);           
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonExistujuciActionPerformed

    private void jTableExistujuciCestujuciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableExistujuciCestujuciMouseClicked
        int selectedRow = -1;
        selectedRow = jTableExistujuciCestujuci.getSelectedRow();
        if(selectedRow != -1){
            jButtonExistujuci.setEnabled(true);
        }
               
    }//GEN-LAST:event_jTableExistujuciCestujuciMouseClicked

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExistujuci;
    private javax.swing.JButton jButtonNovy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableExistujuciCestujuci;
    // End of variables declaration//GEN-END:variables
}
