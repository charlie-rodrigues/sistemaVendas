
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.ItemVenda;
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
public class ItemVendaDAO {
   private Connection conexao;
   
   public ItemVendaDAO(){
   this.conexao = new ConexaoBanco().PegarConexao();
   }
   
   //cadastrar itens dos produtos
   public void cadastrarItemProduto(ItemVenda obj){
       try {
           String sql = "insert into tb_itensvendas (venda_id, produto_id, qtd, subtotal)values(?,?,?,?)";
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, obj.getVenda().getId());
           stmt.setInt(2, obj.getProduto().getId());
           stmt.setInt(3, obj.getQtd());
           stmt.setDouble(4, obj.getSubtotal());
           
           stmt.execute();
           stmt.close();
          // JOptionPane.showMessageDialog(null, "item cadastrado");
           
       } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao cadastrar o ITEM  "+e);
       }
   }
   //método que lista os itens de uma deperminada venda
   public List<ItemVenda>listaItensDaVenda(int venda_id){
       try {
           List<ItemVenda> lista = new ArrayList<>();
           String sql = "select  p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i inner join tb_produtos as p on(i.produto_id=p.id) where i.venda_id =?";
           PreparedStatement stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, venda_id);
           ResultSet rs = stmt.executeQuery();
           
           while(rs.next()){
           ItemVenda item = new ItemVenda();
               Produto prod = new Produto();
               
              // item.setId(rs.getInt("i.id"));
               prod.setDescricao(rs.getString("p.descricao"));
               item.setQtd(rs.getInt("i.qtd"));
               prod.setPreco(rs.getDouble("p.preco"));
               item.setSubtotal(rs.getDouble("i.subtotal"));
               item.setProduto(prod);
               lista.add(item);
           }
           return lista;
       } catch (SQLException e) {
       throw new RuntimeException("erro ao listar os ITENS...");
              }
   }
}
