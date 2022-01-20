package jdbc_basic.dao;

import jdbc_basic.modelo.Categoria;
import jdbc_basic.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {

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

    public void salvarComCategoria(Produto produto) throws SQLException {

        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";

        try (PreparedStatement acao = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            acao.setString(1, produto.getNome());
            acao.setString(2, produto.getDescricao());
            acao.setInt(3, produto.getCategoriaId());

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

            trasformarResultSetEmProduto(produtos, acao);

            }return produtos;
        }


    public List<Produto> buscar(Categoria ct)  throws SQLException{
        List<Produto> produtos = new ArrayList<Produto>();

        String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";

        try(PreparedStatement acao = connection.prepareStatement(sql)){
            acao.setInt(1, ct.getId());

            acao.execute();

            trasformarResultSetEmProduto(produtos, acao);

            }return produtos;
        }

    public void deletar(Integer id) throws SQLException {
        try (PreparedStatement acao = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
            acao.setInt(1, id);
            acao.execute();
        }
    }

    public void alterar(String nome, String descricao, Integer id) throws SQLException {
        try (PreparedStatement acao = connection
                .prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
            acao.setString(1, nome);
            acao.setString(2, descricao);
            acao.setInt(3, id);
            acao.execute();
        }
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));

                produtos.add(produto);
            }

        }
    }
}

