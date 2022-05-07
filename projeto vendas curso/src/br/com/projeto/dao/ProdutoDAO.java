
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
import br.com.projeto.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Charlie
 */
public class ProdutoDAO {
  private Connection conexao;
  
  public ProdutoDAO(){
  this.conexao = new ConexaoBanco().PegarConexao();
  }
  //método cadastrar produto
  public void cadastrarProduto(Produto obj){
      try {
          String sql = "insert into tb_produtos (descricao, preco, qtd_estoque, for_id)values(?,?,?,?)";
          PreparedStatement stmt = conexao.prepareCall(sql);
          stmt.setString(1, obj.getDescricao());
          stmt.setDouble(2, obj.getPreco());
          stmt.setInt(3, obj.getQtd_estoque());
          stmt.setInt(4, obj.getFornecedor().getId());
          
          stmt.execute();
          stmt.close();
          
          JOptionPane.showMessageDialog(null, "produto cadastrado com sucesso!");
      } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"erro ao cadastrar o produto! "+ e);
      }
  
  }
  //método listar produtos
  public List<Produto>listarProduto(){
      try {
          
          List<Produto>lista = new ArrayList<>();
          String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores "
                  + "as f on(p.for_id=f.id)";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          
          while(rs.next()){
              Fornecedor f = new Fornecedor();
              Produto obj = new Produto();
              obj.setId(rs.getInt("p.id"));
              obj.setDescricao(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
              
              f.setNome(rs.getString("f.nome"));
              obj.setFornecedor(f);
              
              lista.add(obj);
              
          }
          return lista;
          
          
      } catch (SQLException e) {
       JOptionPane.showMessageDialog(null,"erro ao criar a lista! "+ e);
       return null;
      }
}
  //método alterar
  public void alterarProduto(Produto obj){
      try {
          String sql = "update tb_produtos set descricao=?, preco=?, qtd_estoque=?, for_id=? where id=? ";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setString(1, obj.getDescricao());
          stmt.setDouble(2, obj.getPreco());
          stmt.setInt(3, obj.getQtd_estoque());
          stmt.setInt(4, obj.getFornecedor().getId());
          stmt.setInt(5, obj.getId());
          
          stmt.execute();
          stmt.close();
          
          JOptionPane.showMessageDialog(null, "produto alterado com sucesso!");
      } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"erro ao alterar o produto!"+ e);
      }
  }
  
  //método excluir
  public void excluirProduto(Produto obj){
      try {
          String sql = "delete from tb_produtos where id=?";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, obj.getId());
          stmt.execute();
          stmt.close();
          JOptionPane.showMessageDialog(null, "produto excluído com sucesso!");
      } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"erro ao excluir o produto!"+ e);
      }
  }
  //método pesquisar produto pelo nome
  public List<Produto>pesquisarProdutoPorNome(String nome){
      try {
          List<Produto> lista = new ArrayList<>();
          String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) "
                  + " where p.descricao like ?";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
          
          while(rs.next()){
           Produto obj = new Produto();
           Fornecedor f = new Fornecedor();
           obj.setId(rs.getInt("p.id"));
           obj.setDescricao(rs.getString("p.descricao"));
           obj.setPreco(rs.getDouble("p.preco"));
           obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
           f.setNome(rs.getString("nome"));
           obj.setFornecedor(f);
           lista.add(obj);
          }
          return lista;
      } catch (SQLException e) {
      JOptionPane.showMessageDialog(null,"erro ao criar a lista"+ e);
      return null;
      }
  }
  //método buscar produto
  public Produto buscarProduto(String nome){
      try {
          String sql ="select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.descricao=?";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setString(1, nome);
          ResultSet rs = stmt.executeQuery();
            
          Produto obj = new Produto();
              Fornecedor f = new Fornecedor();
          if(rs.next()){
             
              obj.setId(rs.getInt("p.id"));
              obj.setDescricao(rs.getString("p.descricao"));
              obj.setPreco(rs.getDouble("p.preco"));
              obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
              
              f.setNome(rs.getString("f.nome"));
              obj.setFornecedor(f);
             
          }
            return obj;
      } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"um erro ao buscar o produto aconteceu!" +e);
          return null;
      }
  }
  //método consultar produto por código
  public Produto buscarPodutoPorCodigo(int id){
      try {
          String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id) where p.id = ?";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setInt(1, id);
         ResultSet rs = stmt.executeQuery();
         
         Produto obj = new Produto();
         Fornecedor f = new Fornecedor();
         if(rs.next()){
         obj.setDescricao(rs.getString("p.descricao"));
         obj.setPreco(rs.getDouble("p.preco"));
         obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
         f.setNome(rs.getString("f.nome"));
         obj.setFornecedor(f);
         
         }
         return obj;
      } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"um erro aconteceu ao buscar o produto"+ e);
         return null;
      }
  }
  //método que da baixa no estoque
  public void baixaEstoque(int id, int qtd_nova){
      try {
          String sql = "update tb_produtos set qtd_estoque=? where id=?";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, qtd_nova);
          stmt.setInt(2, id);
          stmt.execute();
          stmt.close();
          
      } catch (SQLException e) {
     JOptionPane.showMessageDialog(null,"um erro ao dar baixa no estoque aconteceu" +e);
      }
  }
  //método que retorna a quantidade atual do estoque
  public int retornaQtdAtual(int id){
      try {
         int qtd_atual_estoque = 0;
          String sql = "select qtd_estoque from tb_produtos where id =?";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, id);
          ResultSet rs = stmt.executeQuery();
          if(rs.next()){
              qtd_atual_estoque = (rs.getInt("qtd_estoque"));
          }
          return qtd_atual_estoque;
      } catch (SQLException e) {
           throw new RuntimeException("ERRO NO MÉTODO QUE RETORNA A QTD ATUAL DO ESTOQUE "+e);
     
      }
  
  }
  //método adicionar estoque
    public void adionarEstoque(int id, int qtd_nova){
      try {
          String sql = "update tb_produtos set qtd_estoque=? where id=?";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setInt(1, qtd_nova);
          stmt.setInt(2, id);
          stmt.execute();
          stmt.close();
          
      } catch (SQLException e) {
     JOptionPane.showMessageDialog(null,"um erro ao dar baixa no estoque aconteceu" +e);
      }
  }
    
    
    
      
}
