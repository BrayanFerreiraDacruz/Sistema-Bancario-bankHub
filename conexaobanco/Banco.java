package conexaobanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Contas.Conta;
import Contas.Cliente;

public class Banco {

    public Conta criarConta(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO contas (nome_cliente, cpf_cliente, endereco, saldo) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setDouble(4, 0.0);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new Conta(rs.getInt(1), cliente);
            }
        }
        return null;
    }

    public void registrarErro(Integer numeroConta, String tipo, String mensagem) {
        String sql = "INSERT INTO logs_erros (numero_conta, tipo_erro, mensagem) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (numeroConta != null) stmt.setInt(1, numeroConta);
            else stmt.setNull(1, java.sql.Types.INTEGER);
            stmt.setString(2, tipo);
            stmt.setString(3, mensagem);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Erro ao gravar log: " + e.getMessage());
        }
    }

    public Conta buscarConta(int numero) {
        String sql = "SELECT * FROM contas WHERE numero = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente(rs.getString("nome_cliente"), rs.getString("cpf_cliente"),
                        rs.getString("endereco"));
                Conta conta = new Conta(rs.getInt("numero"), c);


                double saldoDb = rs.getDouble("saldo");
                conta.setSaldo(saldoDb);
                System.out.println(">>> [BANCO] Conta " + numero + " encontrada. Saldo lido do DB: R$ " + saldoDb);


                return conta;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta: " + e.getMessage());
        }
        return null;
    }

    public void atualizarSaldoNoBanco(Conta conta) {
        String sql = "UPDATE contas SET saldo = ? WHERE numero = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumero());
            stmt.executeUpdate();
            System.out.println(">>> [BANCO] Saldo da conta " + conta.getNumero() + " atualizado para: R$ " + conta.getSaldo());
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }

    public List<Conta> listarContas() {
        List<Conta> lista = new ArrayList<>();
        String sql = "SELECT * FROM contas";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente(rs.getString("nome_cliente"), rs.getString("cpf_cliente"), rs.getString("endereco"));
                Conta conta = new Conta(rs.getInt("numero"), c);
                conta.setSaldo(rs.getDouble("saldo"));
                lista.add(conta);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contas: " + e.getMessage());
        }
        return lista;
    }
}