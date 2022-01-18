import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();

        Statement acao = connection.createStatement();

        acao.execute("DELETE FROM PRODUTO WHERE ID > 2");

        Integer linhasRemovidas = acao.getUpdateCount();

        System.out.println("Quantidade de linhas que foram removidas: " + linhasRemovidas);

        }
    }

