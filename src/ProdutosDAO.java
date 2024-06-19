/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (String nomeProduto, int valorProduto){
        conn = new conectaDAO().connectDB();
        prep = null;
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, nomeProduto);
            prep.setInt(2, valorProduto);
            prep.setString(3, "A Venda");
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "O cadastro do produto foi realizado com sucesso!");
        }
        catch (SQLException exc) {
            System.out.println("Cadastro do produto não foi realizado!\nErro ao acessar o banco de dados: \n" + exc.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos";
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
            if (listagem.size() > 12) {
                for (int contador = 0; contador < 12; contador++) {
                    listagem.set(contador, listagem.get(contador));
                }
            }
            return listagem;
        }
        catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao buscar informações no banco de dados!\nErro ao acessar o banco de dados: \n" + exc.getMessage());
            return null;
        }
    }
    
    
    
        
}

