package jdbc_basic.dao;

import jdbc_basic.modelo.Categoria;
import jdbc_basic.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Classe responsável pelas regras de negócio relacionado a produtos, ela irá receber dados da classe controller efetuar alterações no banco de dados.


public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {this.connection = connection;}

    public void salvar(Produto produto){
        try {
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";
            try (PreparedStatement acao = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                acao.setString(1, produto.getNome());
                acao.setString(2, produto.getDescricao());
                acao.execute();
                try (ResultSet rst = acao.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarComCategoria(Produto produto) {
        try {
            String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, CATEGORIA_ID) VALUES (?, ?, ?)";
            try (PreparedStatement acao = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                acao.setString(1, produto.getNome());
                acao.setString(2, produto.getDescricao());
                acao.setInt(3, produto.getCategoriaId());
                acao.execute();
                try (ResultSet rst = acao.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listar() {
        try {
            List<Produto> produtos = new ArrayList<Produto>();
            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
            try (PreparedStatement acao = connection.prepareStatement(sql)) {
                acao.execute();
                trasformarResultSetEmProduto(produtos, acao);
            }
            return produtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> buscar (Categoria ct) {
        try {
            List<Produto> produtos = new ArrayList<Produto>();
            String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";
            try (PreparedStatement acao = connection.prepareStatement(sql)) {
                acao.setInt(1, ct.getId());
                acao.execute();
                   trasformarResultSetEmProduto(produtos, acao);
            }
            return produtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar (Integer id){
        try (PreparedStatement acao = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID = ?")) {
            acao.setInt(1, id);
            acao.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar (String nome, String descricao, Integer id) {
        try (PreparedStatement acao = connection
                .prepareStatement("UPDATE PRODUTO P SET P.NOME = ?, P.DESCRICAO = ? WHERE ID = ?")) {
            acao.setString(1, nome);
            acao.setString(2, descricao);
            acao.setInt(3, id);
            acao.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmProduto (List<Produto> produtos, PreparedStatement acao) throws SQLException {
        try (ResultSet rst = acao.getResultSet()) {
            while (rst.next()) {
                Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                produtos.add(produto);
            }
        }
    }
}

