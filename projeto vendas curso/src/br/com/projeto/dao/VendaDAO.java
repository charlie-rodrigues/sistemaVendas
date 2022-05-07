package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Cliente;
import br.com.projeto.model.ItemVenda;
import br.com.projeto.model.Produto;
import br.com.projeto.model.Venda;
import br.com.projeto.view.FormPagamentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Charlie
 */
public class VendaDAO {
ConexaoBanco con = new ConexaoBanco();
    private Connection conexao;

    public VendaDAO() {
        this.conexao = new ConexaoBanco().PegarConexao();
    }
    //método cadastrar venda
    public void cadastrarVenda(Venda obj){
        try {
            String sql ="insert into tb_vendas (cliente_id, data_venda,total_venda, observacoes)values(?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObservacoes());
            
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "venda registrada com sucesso!");
        } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "erro ao registrar a venda"+e);
        }
    }
      //método retornar a última venda
    public int retornaUltimaVenda(){
        try {
            int idUltimaVenda = 0;
            String sql = "select max(id) id from tb_vendas";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            Venda v = new Venda();
            v.setId(rs.getInt("id"));
            
            idUltimaVenda = v.getId();
            }
            return idUltimaVenda;
        } catch (SQLException e) {
       throw new RuntimeException("um erro ao retornar a útima venda aconteceu "+e);
        }

    }
    //método histórico de vendas
    public List<Venda>historicoDeVendas(LocalDate data_inicio, LocalDate data_fim){
        try {
            List<Venda>lista = new ArrayList<>();
            
            String sql = "select v.id, date_format(v.data_venda, '%d/%m/%y') as data_formatada, c.nome,v.total_venda, v.observacoes from tb_vendas as v inner join tb_clientes as c on(v.cliente_id=c.id) where v.data_venda BETWEEN ? and ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
            Venda obj = new Venda();
                Cliente c = new Cliente();
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObservacoes(rs.getString("v.observacoes"));
                
                obj.setCliente(c);
                lista.add(obj);
            }
            return lista;
        } catch (SQLException e) {
        throw new RuntimeException("erro ao criar o histórico de vendas! "+e);
        }
    }
    //método que retorna o movimento diário
    public double retornaTotalVendaPelaData(LocalDate data_venda){
        try {
            double total_venda = 0;
            String sql = "select sum(total_venda)as total from tb_vendas where data_venda = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, data_venda.toString());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
              
            total_venda = rs.getDouble("total");
            }
            return  total_venda;
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    
  
   
    }
      public void relVendas(ItemVenda obj) {
//    FormPagamentos tela = new FormPagamentos();
// private Connection conexao;
    
//        try {
//            this.conexao = new ConexaoBanco().PegarConexao();
//            con.conecta();
//            String sql = "SELECT i.id, i.venda_id, i.qtd, p.descricao, i.subtotal from tb_itensvendas as i inner JOIN tb_produtos as p on(i.produto_id=p.id) WHERE  i.venda_id = ?";
//
//            PreparedStatement stmt = conexao.prepareStatement(sql);
//
//            stmt.setInt(1, obj.getVenda().getId());
//            ResultSet rs = stmt.executeQuery();
//            JRResultSetDataSource jrRS = new JRResultSetDataSource(con.resultSet);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(
//                    "src/br.com.projeto.dao/relVendas.jasper", new HashMap(), jrRS);
//            JasperViewer.viewReport(jasperPrint, false);
//
////      while(rs.next()){
//////          VendaDAO v = new VendaDAO();
//////          ItemVenda obj_item = new ItemVenda();
//////          Produto p = new Produto();
////        
////        
////      }
//        } catch (Exception erro) {
//            JOptionPane.showMessageDialog(null, "deu erro =" + erro);
//        }

    }
    
}

