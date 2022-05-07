
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedor;
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
public class FornecedorDAO {
  private Connection conexao;
  
  public FornecedorDAO(){
   this.conexao = new ConexaoBanco().PegarConexao();
 }
  
  //cadastrar fornecedores
  public void cadastrarFornecedores(Fornecedor obj){
      try {
          String sql = "insert into tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado )"
                  + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
          PreparedStatement stmt = conexao.prepareStatement(sql);
          stmt.setString(1, obj.getNome());
          stmt.setString(2, obj.getCnpj());
          stmt.setString(3, obj.getEmail());
          stmt.setString(4, obj.getTelefone());
          stmt.setString(5, obj.getCelular());
          stmt.setString(6, obj.getCep());
          stmt.setString(7, obj.getEndereco());
          stmt.setInt(8, obj.getNumero());
          stmt.setString(9, obj.getComplemento());
          stmt.setString(10, obj.getBairro());
          stmt.setString(11, obj.getCidade());
          stmt.setString(12, obj.getEstado());
          
          stmt.execute();
          stmt.close();
          JOptionPane.showMessageDialog(null, "fornecedor cadastrado com sucesso");
                    
      } catch (SQLException e) {
          JOptionPane.showMessageDialog(null,"erro ao cadastrar o fornecedor:"+ e);
      }
      
     
     
  }
    //método alterar fornecedores
      public void alterarFornecedores(Fornecedor obj){
          try {
              String sql = "update tb_fornecedores set nome=?,cnpj=?,email=?,telefone=?,celular=?,cep=?,endereco=?, numero=?,complemento=?,"
                      + "bairro=?,cidade=?,estado=? where id=? ";
              PreparedStatement stmt = conexao.prepareStatement(sql);
              stmt.setString(1, obj.getNome());
              stmt.setString(2, obj.getCnpj());
              stmt.setString(3, obj.getEmail());
              stmt.setString(4, obj.getTelefone());
              stmt.setString(5, obj.getCelular());
              stmt.setString(6, obj.getCep());
              stmt.setString(7, obj.getEndereco());
              stmt.setInt(8, obj.getNumero());
              stmt.setString(9, obj.getComplemento());
              stmt.setString(10, obj.getBairro());
              stmt.setString(11, obj.getCidade());
              stmt.setString(12, obj.getEstado());
              stmt.setInt(13, obj.getId());
              
              stmt.execute();
              stmt.close();
              
              JOptionPane.showMessageDialog(null, "fornecedor alterado com sucesso");
              
          } catch (SQLException e) {
              JOptionPane.showMessageDialog(null,"erro ao alterar o fornecedor! "+ e);
          }
      }
      
   //método excluir fornecedores   
     public void excluirFornecedores(Fornecedor obj){
         try {
             String sql = "delete from tb_fornecedores where id=?";
             PreparedStatement stmt = conexao.prepareStatement(sql);
             stmt.setInt(1, obj.getId());
             
             stmt.execute();
             stmt.close();
             
             JOptionPane.showMessageDialog(null, "fornecedor excluído com sucesso!");
         } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"erro ao excluir o fornecedor"+ e);
         }
     }
     public List<Fornecedor>listarFornecedores(){
       List<Fornecedor> lista = new ArrayList<>();
         try {
             String sql = "select * from tb_fornecedores ORDER BY NOME";
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs  = stmt.executeQuery();
             
             while(rs.next()){
                 Fornecedor obj = new Fornecedor();
                 obj.setId(rs.getInt("id"));
                 obj.setNome(rs.getString("nome"));
                 obj.setCnpj(rs.getString("cnpj"));
                 obj.setTelefone(rs.getString("telefone"));
                 obj.setCelular(rs.getString("celular"));
                 obj.setEmail(rs.getString("email"));
                 obj.setCep(rs.getString("cep"));
                 obj.setEndereco(rs.getString("endereco"));
                 obj.setNumero(rs.getInt("numero"));
                 obj.setComplemento(rs.getString("complemento"));
                 obj.setBairro(rs.getString("bairro"));
                 obj.setCidade(rs.getString("cidade"));
                 obj.setEstado(rs.getString("estado"));
                 
                 lista.add(obj);
             }
         return lista;
             
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"erro ao criar a lista "+e);
             return null;
         }
     }
     //método pesquisar por nome
     public List<Fornecedor>pesquisarNome(String nome){
         try {
         List<Fornecedor> lista = new ArrayList<>();
         String sql = "select * from tb_fornecedores where nome like ?";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setString(1, nome);
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()){
             Fornecedor obj = new Fornecedor();
             obj.setId(rs.getInt("id"));
             obj.setNome(rs.getString("nome"));
             obj.setCnpj(rs.getString("cnpj"));
             obj.setEmail(rs.getString("email"));
             obj.setTelefone(rs.getString("telefone"));
             obj.setCelular(rs.getString("celular"));
             obj.setCep(rs.getString("cep"));
             obj.setEndereco(rs.getString("endereco"));
             obj.setNumero(rs.getInt("numero"));
             obj.setComplemento(rs.getString("complemento"));
             obj.setBairro(rs.getString("bairro"));
             obj.setCidade(rs.getString("cidade"));
             obj.setEstado(rs.getString("estado"));
             
             lista.add(obj);
         }
        return lista;
         
         } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"erro ao criar a lista "+ e);
         return null;
         }
     }
     //buscar fornecedor
     public Fornecedor buscarFornecedor(String nome){
         try {
             String sql = "select * from tb_fornecedores where nome = ?";
             PreparedStatement stmt = conexao.prepareStatement(sql);
             stmt.setString(1, nome);
             ResultSet rs = stmt.executeQuery();
              
             Fornecedor obj = new Fornecedor();
             if(rs.next()){
                 obj.setId(rs.getInt("id"));
                 obj.setNome(rs.getNString("nome"));
                 obj.setCnpj(rs.getNString("cnpj"));
                 obj.setEmail(rs.getNString("email"));
                 obj.setTelefone(rs.getNString("telefone"));
                 obj.setCelular(rs.getNString("celular"));
                 obj.setCep(rs.getNString("cep"));
                 obj.setEndereco(rs.getNString("endereco"));
                 obj.setNumero(rs.getInt("numero"));
                 obj.setComplemento(rs.getNString("complemento"));
                 obj.setBairro(rs.getNString("bairro"));
                 obj.setCidade(rs.getNString("cidade"));
                 obj.setEstado(rs.getNString("estado"));
             }
             return obj;
             
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null,"fornecedor não encontrado "+ e);
             return null;
         }
     }
}
