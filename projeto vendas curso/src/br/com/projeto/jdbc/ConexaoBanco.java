
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

  
 
public class ConexaoBanco {

    final private String driver = "com.mysql.jdbc.Driver";
    final private String url  = "jdbc:mysql://localhost:3306/bdvendas";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultSet;
    
public Connection PegarConexao(){
    try {
        return DriverManager.getConnection("jdbc:mysql://localhost/bdvendas","root","");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"erro ao conectar com a data base"+ e);
    }
return null;
}

 public boolean conecta(){
 boolean result = true;
     try {
         Class.forName(driver);
         conexao = DriverManager.getConnection(url,usuario,senha);
        // JOptionPane.showMessageDialog(null, "conectou com meu relatório!");
     } catch (ClassNotFoundException erroDriver) {
         JOptionPane.showMessageDialog(null, "erro driver"+erroDriver);
         result = false;
     }catch(SQLException erroFonte){
         JOptionPane.showMessageDialog(null, "erro na minha fonte de dados"+erroFonte);
         result = false;
         
     
     }
     return result;
 
 }
 
public void desconecta(){

    boolean result = true;
    try {
        conexao.close();
        JOptionPane.showMessageDialog(null, "banco fechado!");
    } catch (SQLException fechaErro) {
        JOptionPane.showMessageDialog(null, " fecha banco erro "+fechaErro);
        result = false;
    }

}

public void executeSQL(String sql){
    try {
        statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);
    } catch (Exception erroSQL) {
        JOptionPane.showMessageDialog(null, "erro de sql"+erroSQL.getMessage());
    }

}

}
