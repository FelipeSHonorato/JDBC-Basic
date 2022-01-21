package jdbc_basic.controller;

import jdbc_basic.dao.CategoriaDAO;
import jdbc_basic.factory.ConnectionFactory;
import jdbc_basic.modelo.Categoria;

import java.sql.Connection;
import java.util.List;

//Classe responsável por receber dados da DAO e enviar para o front, assim como receber solicitações do front e repassar ações a serem necessarias para a classe CategoriaDAO.


public class CategoriaController {

    private CategoriaDAO categoriaDAO ;

    public CategoriaController()  {
        Connection connection = new ConnectionFactory().recuperarConexao();
                this.categoriaDAO = new CategoriaDAO(connection);
        }

    public List<Categoria> listar()  {
        return this.categoriaDAO.listar();
    }
}
