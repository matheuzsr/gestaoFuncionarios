/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaofuncionarios.view;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author logcomex
 */
public class BuscarFuncionarioView extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    public BuscarFuncionarioView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAtributos = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();
        btnHistoryBonus = new javax.swing.JButton();
        btnAddFuncionario = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();

        lblValor.setText("Nome");

        btnBuscar.setText("Buscar");

        tblAtributos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblAtributos);

        btnFechar.setText("Fechar");
        btnHistoryBonus.setText("Histórico de bônus");
        btnAddFuncionario.setText("Novo funcionário");
        btnVisualizar.setText("Visualizar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblValor)
                        .addGap(18, 18, 18)
                        .addComponent(txtValor)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVisualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddFuncionario)
                        .addGap(18, 18, 18)
                        .addComponent(btnHistoryBonus)
                        .addGap(18, 18, 18)
                        .addComponent(btnFechar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValor))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnHistoryBonus)
                    .addComponent(btnAddFuncionario)
                    .addComponent(btnVisualizar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHistoryBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryBonusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistoryBonusActionPerformed

    private void btnAddFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFuncionarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddFuncionarioActionPerformed

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnFechar() {
        return btnFechar;
    }

    public JButton getBtnVisualizar() {
        return btnVisualizar;
    }

    public JTable getTblAtributos() {
        return tblAtributos;
    }

    public JButton getBtnAddFuncionario() {
        return btnAddFuncionario;
    }

    public JButton getBtnHistoryBonus() {
        return btnHistoryBonus;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFuncionario;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnHistoryBonus;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTable tblAtributos;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
