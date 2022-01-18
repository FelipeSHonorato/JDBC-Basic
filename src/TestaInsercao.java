import java.sql.*;

public class TestaInsercao {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();

        Statement acao = connection.createStatement();

        acao.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('MOUSE', 'MOUSE SEM FIO')", Statement.RETURN_GENERATED_KEYS);

        ResultSet resultado = acao.getGeneratedKeys();

        while(resultado.next()){

            Integer id = resultado.getInt(1);
            System.out.println("O id criado foi " + id);

        }
    }
}