/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaofuncionarios.view;

import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author Aluno
 */
public class GestaoFuncionariosView extends javax.swing.JFrame {

    public GestaoFuncionariosView() {
        super("Gestão de funcionários");

        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates new form GestaoFuncionariosView
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        desktop = new javax.swing.JDesktopPane();
        lblTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblVersao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblArmazenamento = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        addFuncionarioMenu = new javax.swing.JMenuItem();
        buscarFuncionariosMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        calcularSalarioMenu = new javax.swing.JMenuItem();
        calcularSalarioMenu1 = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Total de funcionários cadastrados: ");

        jLabel3.setText("Versão:");

        jLabel5.setText("Armazenamento:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArmazenamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lblTotal)
                .addContainerGap())
            .addComponent(desktop)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(lblVersao)
                        .addComponent(jLabel5)
                        .addComponent(lblArmazenamento))
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu1.setText("Funcionario");

        addFuncionarioMenu.setText("Adicionar funcionário");
        jMenu1.add(addFuncionarioMenu);

        buscarFuncionariosMenu.setText("Buscar Funcionarios");
        buscarFuncionariosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarFuncionariosMenuActionPerformed(evt);
            }
        });
        jMenu1.add(buscarFuncionariosMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Salario");

        calcularSalarioMenu.setText("Calcular salários");
        jMenu2.add(calcularSalarioMenu);

        calcularSalarioMenu1.setText("Estatistica salário");
        jMenu2.add(calcularSalarioMenu1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarFuncionariosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarFuncionariosMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarFuncionariosMenuActionPerformed

    private void calcularSalarioMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularSalarioMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calcularSalarioMenu1ActionPerformed

    private void menuCalcularBonusActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_menuCalcularBonusActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_menuCalcularBonusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestaoFuncionariosView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GestaoFuncionariosView().setVisible(true);

        });
    }

    public JMenuItem getCalcularSalarioMenu() {

        return calcularSalarioMenu;
    }

    public JMenuItem getCalcularSalarioMenu1() {
        return calcularSalarioMenu1;
    }

    public JMenuItem getAddFuncionarioMenu() {
        return addFuncionarioMenu;
    }

    public JMenuItem getBuscarFuncionariosMenu() {
        return buscarFuncionariosMenu;
    }

    public static JDesktopPane getDesktop() {
        return desktop;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public JLabel getLblArmazenamento() {
        return lblArmazenamento;
    }

    public JLabel getLblVersao() {
        return lblVersao;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addFuncionarioMenu;
    private javax.swing.JMenuItem buscarFuncionariosMenu;
    private javax.swing.JMenuItem calcularSalarioMenu;
    private javax.swing.JMenuItem calcularSalarioMenu1;
    private static javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArmazenamento;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVersao;
    // End of variables declaration//GEN-END:variables
}