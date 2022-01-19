package dao;

import modelo.Categoria;
import modelo.Produto;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }

    public void salvarProduto(Produto produto) throws SQLException {

        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

        try (PreparedStatement acao = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            acao.setString(1, produto.getNome());
            acao.setString(2, produto.getDescricao());

            acao.execute();

            try (ResultSet rst = acao.getGeneratedKeys()){
                while (rst.next()){
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {

        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";

        try(PreparedStatement acao = connection.prepareStatement(sql)){

            acao.execute();

            try(ResultSet rst = acao.getResultSet()){
                while (rst.next()){

                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);
                }
            }
        }return produtos;
    }

    public List<Produto> buscar(Categoria ct)  throws SQLException{
        List<Produto> produtos = new ArrayList<Produto>();

        System.out.println("Executando a query de buscar produto por categoria");

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try(PreparedStatement acao = connection.prepareStatement(sql)){
            acao.setInt(1, ct.getId());

            acao.execute();

            try(ResultSet rst = acao.getResultSet()){
                while (rst.next()){

                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                    produtos.add(produto);
                }
            }
        }return produtos;
    }
}
