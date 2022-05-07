/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Cliente;
//import jacontrol.Classes.WebServiceCep;
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
public class ClientesDAO {
    private Connection conexao;

 public ClientesDAO(){
 this.conexao = new ConexaoBanco().PegarConexao();
 }
      
    //método cadastrar cliente
    public void cadastrarCliente(Cliente obj){
        try {
        //1 passo: criar o comendo SQL
        String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //2 passo: conectar ao banco de dados e organizar o sql
            PreparedStatement stmt = conexao.prepareStatement(sql); 
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
         //3 passo: exetutar o SQL e fechar a conexão
         stmt.execute();
         stmt.close();
        
            JOptionPane.showMessageDialog(null, "cliente cadastrado com sucesso!");
        
        } catch (SQLException e) {
            throw new RuntimeException("erro ao cadastrar cliente " +e);
        }
    }
   
    //método alterar cliente
    public void alterarCliente(Cliente obj){
        try {
            //1 passo: criar o sql conectar ao banco e organizar
            String  sql = "update tb_clientes set nome=?, rg=?, cpf=?, email=?, telefone=?, celular=?, cep=?, "
                    + "endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setString(1, obj.getNome());
           stmt.setString(2, obj.getRg());
           stmt.setString(3, obj.getCpf());
           stmt.setString(4, obj.getEmail());
           stmt.setString(5, obj.getTelefone());
           stmt.setString(6, obj.getCelular());
           stmt.setString(7, obj.getCep());
           stmt.setString(8, obj.getEndereco());
           stmt.setInt(9, obj.getNumero());
           stmt.setString(10, obj.getComplemento());
           stmt.setString(11, obj.getBairro());
           stmt.setString(12, obj.getCidade());
           stmt.setString(13, obj.getEstado());
           stmt.setInt(14, obj.getId());
           
          //3 passo: executar e fechar a conexão com o banco
          stmt.execute();
          stmt.close();
          JOptionPane.showMessageDialog(null, "registro do cliente alterado com sucesso!");
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"erro ao alterar!" +e);
        }
    }
        
     //método excluir cliente
    public void excluirCliente(Cliente obj){
        try {
            //1 passo: criar o sql, conectar ao banco e organozar
            String sql = "delete from tb_clientes where id=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            //3 passo: executar e fechar a conexão
            stmt.execute();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "cliente excluído com sucesso!");
        } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"um erro ao excluir aconteceu!"+ e);
        }
    }
    
    
    //método listar cliente
 public List<Cliente>listarCliente(){
     //1 passo: criar a lista
     List<Cliente> lista = new ArrayList<>();
     try {
         //2 passo: criar o comando SQL e conectar ao banco e organizar
         String sql = "select * from tb_clientes";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()){
         Cliente obj = new Cliente();
         obj.setId(rs.getInt("id"));
         obj.setNome(rs.getString("nome"));
         obj.setRg(rs.getString("rg"));
         obj.setCpf(rs.getString("cpf"));
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
         throw new RuntimeException("erro ao criar a lista de clientes!!" +e);
     }
 }
    
 //método pesquisar por nome
 public List<Cliente>pesquisarNome(String nome){
  
     try {
            List<Cliente> lista = new ArrayList<>();
         String sql = "select * from tb_clientes where nome like ?";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setString(1, nome);
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()){
             Cliente obj = new Cliente();
             obj.setId(rs.getInt("id"));
             obj.setNome(rs.getString("nome"));
             obj.setRg(rs.getString("rg"));
             obj.setCpf(rs.getString("cpf"));
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
     } catch (Exception e) {
    throw new RuntimeException("erro ao criar a lista:" +e);
     }
 }
    
 //método BUSCAR cliente pelo nome
 public Cliente BuscarCliente(String nome){
     try {
         String sql = "select * from tb_clientes where nome =?";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setString(1, nome);
         ResultSet rs = stmt.executeQuery();
         
         Cliente obj = new Cliente();
         if(rs.next()){
             obj.setId(rs.getInt("id"));
             obj.setNome(rs.getString("nome"));
             obj.setRg(rs.getString("rg"));
             obj.setCpf(rs.getString("cpf"));
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
         }
         return obj;
         
         
         
     } catch (SQLException e) {
         throw new RuntimeException("erro ao buscar nome: "+e);
     }
 }
   // busca cep
// public Cliente buscaCep(String cep) {
//       
////        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
//       
//
//        Cliente obj = new Cliente();
//
//        if (webServiceCep.wasSuccessful()) {
//            obj.setEndereco(webServiceCep.getLogradouroFull());
//            obj.setCidade(webServiceCep.getCidade());
//            obj.setBairro(webServiceCep.getBairro());
//            obj.setEstado(webServiceCep.getUf());
//            return obj;
//        } else {
//            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
//            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
//            return null;
//        }
//
//    } 
 //método buscar cliente por cpf no formulário de vendas
 public Cliente buscarClientePorCpf(String cpf){
     try {
         String sql = "select * from tb_clientes where cpf=?";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setString(1, cpf);
         ResultSet rs = stmt.executeQuery();
         
         Cliente obj = new Cliente();
         if(rs.next()){
         obj.setId(rs.getInt("id"));
         obj.setNome(rs.getString("nome"));
         obj.setRg(rs.getString("rg"));
         obj.setCpf(rs.getString("cpf"));
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
         }
         return obj;
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"um erro ao buscar o cliente por cpf aconteceu..."+ e);
     return null;
     }
 }
    
}
