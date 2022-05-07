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
public class relVendas {
    ConexaoBanco con = new ConexaoBanco();

    public relVendas() {
        try {
            con.conecta();
            con.executeSQL("SELECT i.id, i.venda_id, p.descricao,p.preco, i.qtd, i.subtotal FROM tb_itensvendas AS i inner join tb_produtos as p on(i.produto_id=p.id) WHERE venda_id = (SELECT MAX(id) FROM tb_vendas)");
            JRResultSetDataSource jrRS = new JRResultSetDataSource(con.resultSet);
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:/Users/deskw/Documents/NetBeansProjects/projeto vendas curso/src/relatorios/relVendas.jasper",
                     new HashMap() , jrRS);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"deu erro relJasper"+ e.getMessage());
        }
    }
    
    
    
}
