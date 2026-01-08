package conexaobanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // Pegamos os valores do Sistema Operacional, não do código escrito
    private static final String URL = "jdbc:postgresql://localhost:5432/SistemaBancario";
    private static final String USUARIO = System.getenv("DB_USER");
    private static final String SENHA = System.getenv("DB_PASSWORD");

    public static Connection getConexao() {
        try {
            // Se as variáveis não estiverem configuradas, avisar
            if (USUARIO == null || SENHA == null) {
                throw new SQLException("Variáveis de ambiente DB_USER ou DB_PASSWORD não encontradas!");
            }
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("❌ Erro de conexão: " + e.getMessage());
            return null;
        }
    }
}