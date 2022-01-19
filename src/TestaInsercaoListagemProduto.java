import dao.ProdutoDAO;
import modelo.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoListagemProduto {


    public static void main(String[] args) throws SQLException {

        Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {

            ProdutoDAO produtoDao = new ProdutoDAO(connection);
            produtoDao.salvarProduto(comoda);
            List<Produto> listadeProdutos = produtoDao.listar();
            listadeProdutos.stream().forEach(lp -> System.out.println(lp));

        }

    }
}
