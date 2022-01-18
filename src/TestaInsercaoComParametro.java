import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {

        String nome ="TELEVISÃO";
        String descricao ="LG";

        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();

        PreparedStatement acao = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);

        acao.setString(1, nome);
        acao.setString(2, descricao);

        acao.execute();

        ResultSet resultado = acao.getGeneratedKeys();

        while (resultado.next()) {

            Integer id = resultado.getInt(1);
            System.out.println("O id criado foi " + id);

        }
    }
}
