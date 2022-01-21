package jdbc_basic.controller;

import jdbc_basic.dao.ProdutoDAO;
import jdbc_basic.factory.ConnectionFactory;
import jdbc_basic.modelo.Produto;

import java.sql.Connection;
import java.util.List;

//Classe responsável por receber dados da DAO e enviar para o front, assim como receber solicitações do front e repassar ações a serem necessarias para a classe ProdutoDAO.

public class ProdutoController {

    private ProdutoDAO produtoDAO;

    public ProdutoController()  {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.produtoDAO = new ProdutoDAO(connection);
    }

    public void deletar(Integer id) {
       this.produtoDAO.deletar(id);
    }

    public void salvar(Produto produto) {
       this.produtoDAO.salvar(produto);
    }

    public List<Produto> listar() {
        return this.produtoDAO.listar();
    }

    public void alterar(String nome, String descricao, Integer id) {
        this.produtoDAO.alterar(nome,descricao,id);
    }
}
