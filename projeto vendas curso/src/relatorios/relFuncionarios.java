/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import br.com.projeto.jdbc.ConexaoBanco;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author deskw
 */
public class relFuncionarios {
    ConexaoBanco con = new ConexaoBanco();

    public relFuncionarios() {
        try {
            con.conecta();
            con.executeSQL("select * from tb_funcionarios");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:/Users/deskw/Documents/NetBeansProjects/projeto vendas curso/src/relatorios/relFuncionarios.jasper",
                     new HashMap() , jrRS);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"deu erro relJasper"+ e.getMessage());
        }
    }
    
    
    
}
