/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.multiscrap.telas;
import java.sql.*;
import br.com.multiscrap.dal.ModuloConexao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author dryro
 */
public class TelaProdutos extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form TelaProdutos
     */
    public TelaProdutos() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void cadastrar(){
        String sql = "insert into produtos (produto, valor) values (?,?)";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtNome.getText());
            pst.setString(2,txtPreco.getText().replace(",","."));
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
                txtNome.setText(null);
                txtPreco.setText(null);                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        } 
    }
    
    private void pesquisar_produto(){
        String  sql= "select codigo as Codigo, produto as Produto, valor as Valor from produtos where produto like ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtPesquisa.getText() + "%");
            rs = pst.executeQuery();
            tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
            btnADC.setEnabled(false);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void setar_campos(){
        int setar = tblProdutos.getSelectedRow();
        txtCodigo.setText(tblProdutos.getModel().getValueAt(setar,0).toString());    
        txtNome.setText(tblProdutos.getModel().getValueAt(setar,1).toString());  
        txtPreco.setText(tblProdutos.getModel().getValueAt(setar,2).toString());  
    }
    
    private void alterar(){
        String sql = "update produtos set produto=?, valor=? where codigo=?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtNome.getText());
            pst.setString(2,txtPreco.getText().replace(",",".")); 
            pst.setString(3,txtCodigo.getText());
            int adicionado = pst.executeUpdate();
            if(adicionado > 0){
                JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
                txtCodigo.setText(null);
                txtNome.setText(null);
                txtPreco.setText(null);
                btnADC.setVisible(true);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);            
        } 
    }
    
    private void excluir(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o produto?", "Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            String sql = "delete from produtos where codigo=?";
            try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtCodigo.getText());
            int apagado = pst.executeUpdate();
            if(apagado > 0){
                JOptionPane.showMessageDialog(null,"Produto excluído com sucesso"); 
                txtCodigo.setText(null);
                txtNome.setText(null);
                txtPreco.setText(null);
                btnADC.setEnabled(true);
            }
        }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);                
            }
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
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        txtPesquisa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnADC = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        expProdutos = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Produtos");

        jLabel1.setText("Nome");

        jLabel2.setText("Preço");

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Produto", "Valor"
            }
        ));
        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProdutos);

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cadastrar ");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/multiscrap/icones/pesquisar.png"))); // NOI18N

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/multiscrap/icones/alterar3.png"))); // NOI18N
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/multiscrap/icones/apagar.png"))); // NOI18N
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnADC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/multiscrap/icones/salve.png"))); // NOI18N
        btnADC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADCActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Pesquisar");

        jLabel6.setText("Código");

        txtCodigo.setEnabled(false);

        expProdutos.setText("Exportar");
        expProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expProdutosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(92, 92, 92)
                        .addComponent(expProdutos))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(159, 159, 159)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(24, 24, 24)
                                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addComponent(btnADC, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(btnADC))
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnApagar)
                            .addComponent(btnAlterar))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(expProdutos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setBounds(0, 0, 751, 567);
    }// </editor-fold>//GEN-END:initComponents

    private void btnADCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADCActionPerformed
        cadastrar();
    }//GEN-LAST:event_btnADCActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        excluir();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
        
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void tblProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProdutosMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblProdutosMouseClicked

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
        pesquisar_produto();
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void expProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expProdutosActionPerformed
        // TODO add your handling code here:
        List lista = new ArrayList();
        for(int i = 0; i<tblProdutos.getRowCount(); i++){
            ListaProdutos prod = new ListaProdutos(
                    tblProdutos.getValueAt(i, 0).toString(), 
                    tblProdutos.getValueAt(i, 1).toString(), 
                    tblProdutos.getValueAt(i, 2).toString());
            lista.add(prod);
        }
        
        try {
            JasperReport exportar = (JasperReport) JRLoader.loadObject("listaProduto.jasper");
            Map parametro = new HashMap();
            JasperPrint jprint = JasperFillManager.fillReport(exportar,parametro,new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(jprint);
        } catch (JRException ex) {
            Logger.getLogger(TelaProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_expProdutosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADC;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton expProdutos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}