/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.multiscrap.telas;
import java.sql.*;
import br.com.multiscrap.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author dryro
 */
public class TelaEncomendas extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaEncomendas
     */
    public TelaEncomendas() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void pesquisar_enc(){
        String sql =  "select numero as Numero, data_enc as Cadastro, data_entrega as Entrega, valor as Valor, situacao as Situação, descricao as Descrição, id as ID_Cliente from encomendas where data_enc like ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtDataEntrega.getText() + "%");
            rs = pst.executeQuery();
            tblEncomendas.setModel(DbUtils.resultSetToTableModel(rs));            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void setar(){
        int setar = tblEncomendas.getSelectedRow();
        EditEnc.setText(tblEncomendas.getModel().getValueAt(setar,5).toString());
        txtNumero.setText(tblEncomendas.getModel().getValueAt(setar,0).toString());
        String sql =  "select produto as Produto, quantidade as Quantidade from itens_venda where numero=?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNumero.getText());
            rs = pst.executeQuery();
            tblEncomendas1.setModel(DbUtils.resultSetToTableModel(rs));           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
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
        txtDataEntrega = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEncomendas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        EditEnc = new javax.swing.JEditorPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEncomendas1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Encomendas");

        jLabel1.setText("Data");

        txtDataEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataEntregaKeyReleased(evt);
            }
        });

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        tblEncomendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número", "Data Cadastro", "Data Entrega", "Valor", "Situação", "ID Cliente"
            }
        ));
        tblEncomendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEncomendasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEncomendas);

        jLabel3.setText("Descrição");

        jScrollPane2.setViewportView(EditEnc);

        tblEncomendas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade"
            }
        ));
        tblEncomendas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEncomendas1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblEncomendas1);

        jLabel2.setText("Produtos");

        txtNumero.setEnabled(false);

        jLabel4.setText("Número Encomenda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        setBounds(0, 0, 751, 569);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtDataEntregaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataEntregaKeyReleased
        pesquisar_enc();
    }//GEN-LAST:event_txtDataEntregaKeyReleased

    private void tblEncomendasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEncomendasMouseClicked
        setar();
    }//GEN-LAST:event_tblEncomendasMouseClicked

    private void tblEncomendas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEncomendas1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblEncomendas1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane EditEnc;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblEncomendas;
    private javax.swing.JTable tblEncomendas1;
    private javax.swing.JTextField txtDataEntrega;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
