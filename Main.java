import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Conexao {
    public static Connection getConexao() {
        try {
            // Use a senha que você definiu na instalação do Postgres
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/SistemaBancario",
                    "postgres",
                    "147604"
            );
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}