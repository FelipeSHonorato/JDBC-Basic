import java.sql.*;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {


        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();

        PreparedStatement acao = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
        acao.setInt(1, 2);
        acao.execute();

        Integer linhasRemovidas = acao.getUpdateCount();

        System.out.println("Quantidade de linhas que foram removidas: " + linhasRemovidas);

        }
    }

