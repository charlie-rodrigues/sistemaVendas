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
public class relProdutos {
    ConexaoBanco con = new ConexaoBanco();

    public relProdutos() {
        try {
            con.conecta();
            con.executeSQL("select p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p inner join tb_fornecedores as f on(p.for_id=f.id)");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:/Users/deskw/Documents/NetBeansProjects/projeto vendas curso/src/relatorios/relProdutos.jasper",
                     new HashMap() , jrRS);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"deu erro relJasper"+ e.getMessage());
        }
    }
    
    
    
}
