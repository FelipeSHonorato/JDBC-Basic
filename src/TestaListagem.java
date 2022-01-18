import java.sql.*;


public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory criaConexao = new ConnectionFactory();
        Connection connection = criaConexao.recuperarConexao();

        Statement stm = connection.createStatement();
        stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");

        ResultSet resultados = stm.getResultSet();

        while (resultados.next()){
            Integer id= resultados.getInt("ID");
            System.out.println(id);

            String nome = resultados.getString("NOME");
            System.out.println(nome);

            String descricao = resultados.getString("DESCRICAO");
            System.out.println(descricao);
        }

        System.out.println("Fechando Conexão!!");

        connection.close();

    }
}
