
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Funcionario;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
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
public class FuncionarioDAO {
    private Connection conexao;
    
    public FuncionarioDAO(){
    this.conexao = new ConexaoBanco().PegarConexao();
    }
    //método cadastrar funcionários
    public void cadastrarFuncionarios(Funcionario obj){
        try {
            String sql = "insert into tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso,telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivel_acesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getEstado());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "funcionário cadastrado com sucesso!");
                       
            
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"erro ao cadastrar funcionário! "+ e);
        }
    }
    //método alterar
    public void alterarFuncionario(Funcionario obj){
        try {
            String sql = "update tb_funcionarios set nome=?, rg=?,cpf=?,email=?,senha=?,cargo=?,nivel_acesso=?,"
                    + "telefone=?,celular=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=? where id =? ";
            PreparedStatement stmt = conexao.prepareStatement(sql);
             
              stmt.setString(1, obj.getNome());
              stmt.setString(2, obj.getRg());
              stmt.setString(3, obj.getCpf());
              stmt.setString(4, obj.getEmail());
              stmt.setString(5, obj.getSenha());
              stmt.setString(6, obj.getCargo());
              stmt.setString(7, obj.getNivel_acesso());
              stmt.setString(8, obj.getTelefone());
              stmt.setString(9, obj.getCelular());
              stmt.setString(10, obj.getCep());
              stmt.setString(11, obj.getEndereco());
              stmt.setInt(12, obj.getNumero());
              stmt.setString(13, obj.getComplemento());
              stmt.setString(14, obj.getBairro());
              stmt.setString(15, obj.getCidade());
              stmt.setString(16, obj.getEstado());
              
               stmt.setInt(17, obj.getId());
               
               stmt.execute();
               stmt.close();
               
               JOptionPane.showMessageDialog(null, "funcionário alterado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erro ao alterar o funcionário "+ e);
        }
    }
    
    //método excluir
    public void excluirFuncionario(Funcionario obj){
        try {
             String sql = "delete from tb_funcionarios where id=?";
    PreparedStatement stmt = conexao.prepareStatement(sql);
    stmt.setInt(1, obj.getId());
    
    stmt.execute();
    stmt.close();
    JOptionPane.showMessageDialog(null, "funcionário excluído com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erro ao excluir o funcionário "+ e);
        }
    
    }
    
    
    public List<Funcionario>listarFuncionario(){
        try {
            List<Funcionario> lista = new ArrayList<>();
            String sql = "select * from tb_funcionarios";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario obj = new Funcionario();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            throw new RuntimeException("erro ao criar a lista! "+e);
        }
    }
    //método listar funcionários
 public List<Funcionario>pesquisarFuncionarioNome(String nome){
     try {
         List<Funcionario>lista = new ArrayList<>();
         String sql = "select * from tb_funcionarios where nome like ?";
         PreparedStatement stmt = conexao.prepareStatement(sql);
         stmt.setString(1, nome);
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()){
             Funcionario obj = new Funcionario();
             obj.setId(rs.getInt("id"));
             obj.setNome(rs.getString("nome"));
             obj.setRg(rs.getString("rg"));
             obj.setCpf(rs.getString("cpf"));
             obj.setEmail(rs.getString("email"));
             obj.setSenha(rs.getString("senha"));
             obj.setCargo(rs.getString("cargo"));
             obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
         JOptionPane.showMessageDialog(null,"erro ao criar a lista pesquisar nome! "+ e);
         return null;
     }
 }
//método bustcar funcionários
    public Funcionario buscarFuncionariosNome(String nome){
        try {
            String sql = "select * from tb_funcionarios where nome =?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            
            Funcionario obj = new Funcionario();
            if(rs.next()){
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro ao encontrar o funcionário "+e);
            return null;
        }
    }
    //método efetuar login
    public void efetuarLogin(String email, String senha){
        try {
            String sql = "select * from tb_funcionarios where email=? and senha=?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
            //logou
            if(rs.getString("nivel_acesso").equals("Administrador")){
            JOptionPane.showMessageDialog(null, "seja bem vindo ao sistema!");
            FrmMenu tela = new FrmMenu();
            tela.userLogado = rs.getString("nome");
            tela.setVisible(true);
            }
            else if(rs.getString("nivel_acesso").equals("Usuário")){
            JOptionPane.showMessageDialog(null, "Seja bem vindo ao sistema!");
            FrmMenu tela = new FrmMenu();
            tela.userLogado = rs.getString("nome");
            tela.menuFuncionariosjMenuItem2.setEnabled(false);
            tela.posicaoDiajMenuItem7.setEnabled(false);
            
            tela.setVisible(true);
            }
            }else{
                //não logou
                FrmLogin login = new FrmLogin();
                JOptionPane.showMessageDialog(null, "dados inválidos!");
                login.setVisible(true);
            }

        } catch (SQLException e) {
            throw new RuntimeException("erro crítico! "+e);
        }
    }
    
}
