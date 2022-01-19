import java.sql.*;

public class TestaInsercaoComParametro {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory criaConexao = new ConnectionFactory();
        try (Connection connection = criaConexao.recuperarConexao()) {

            connection.setAutoCommit(false);

            try (PreparedStatement acao = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);){

                adicionarEntrada("SmarTV", "45 polegadas", acao);
                adicionarEntrada("Radio", "Radio de bateria", acao);

                connection.commit();

            } catch (Exception e){
                e.printStackTrace();
                System.out.println("RollBack Executado");
                connection.rollback();
            }
        }
    }

    private static void adicionarEntrada(String nome, String descricao, PreparedStatement acao) throws SQLException {
        acao.setString(1, nome);
        acao.setString(2, descricao);

        acao.execute();

        try (  ResultSet resultado = acao.getGeneratedKeys();) {

            while (resultado.next()) {

                Integer id = resultado.getInt(1);
                System.out.println("O id criado foi " + id);
            }
        }
    }

}
