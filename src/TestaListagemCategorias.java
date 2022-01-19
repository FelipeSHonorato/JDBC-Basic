import dao.CategoriaDAO;
import modelo.Categoria;
import modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemCategorias {
    public static void main(String[] args) throws SQLException {

        try(Connection connection = new ConnectionFactory().recuperarConexao()) {

            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);

            List<Categoria> listaCategoria = categoriaDAO.listarComProdutos();

            listaCategoria.forEach(ct -> {
                System.out.println(ct.getNome());

                    for (Produto produto : ct.getProdutos()) {
                        System.out.println(ct.getNome() + " - " + produto.getNome());
                    }

            });
        }
    }
}
