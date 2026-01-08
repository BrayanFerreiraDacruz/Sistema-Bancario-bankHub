package conexaobanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/SistemaBancario";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "147604";

    public static Connection getConexao() {
        try {

            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("❌ Erro de conexão: " + e.getMessage());

            return null;
        }
    }
}