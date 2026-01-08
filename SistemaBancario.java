import Contas.Cliente;
import Contas.Conta;
import conexaobanco.Banco;
import conexaobanco.Conexao;
import endereço.cepservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SistemaBancario {
    private static Scanner scanner = new Scanner(System.in);
    private static final Banco banco = new Banco();
    private static cepservice serviceCep = new cepservice();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: criarConta(); break;
                case 2: criarContacorrente(); break;
                case 3: realizarDeposito(); break;
                case 4: realizarSaque(); break;
                case 5: realizarTransferencia(); break;
                case 6: exibirListaContas(); break; // Ajustado para chamar o método de exibição
                case 7: consultarConta(); break;
                case 8: System.out.println("Saindo do sistema..."); break;
                case 9: exibirLogsErros(); break; // Nova opção de logs
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 8);
    }

    public static void exibirMenu() {
        System.out.println("\n===== MENU BANCÁRIO =====");
        System.out.println("1. Criar Conta | 2. Criar Conta Corrente");
        System.out.println("3. Depósito    | 4. Saque");
        System.out.println("5. Transferir  | 6. Listar Contas");
        System.out.println("7. Consultar   | 8. Sair | 9. Logs de Erros");
        System.out.print("Escolha uma opção: ");
    }

    public static void criarConta() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("CPF (11 dígitos): ");
        String cpf = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        String enderecoApi;
        try {
            enderecoApi = serviceCep.buscaEnderecoPelo(cep);
        } catch (Exception e) {
            enderecoApi = "Endereço manual (CEP: " + cep + ")";
        }

        Cliente cliente = new Cliente(nome, cpf, enderecoApi);
        try {
            Conta conta = banco.criarConta(cliente);
            System.out.println("Conta criada com sucesso! " + conta);
        } catch (Exception e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
            banco.registrarErro(null, "ERRO_CRIACAO_CONTA", e.getMessage());
        }
    }

    public static void criarContacorrente() {
        criarConta();
    }

    public static void realizarDeposito() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        System.out.print("Valor do depósito: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta conta = banco.buscarConta(numero);
        if (conta != null) {
            conta.depositar(valor);
            banco.atualizarSaldoNoBanco(conta);
            System.out.println("Sucesso! Novo saldo: R$ " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
            banco.registrarErro(numero, "DEPOSITO_FALHOU", "Conta inexistente para depósito.");
        }
    }

    public static void realizarSaque() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        System.out.print("Valor do saque: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta conta = banco.buscarConta(numero);
        if (conta != null) {
            if (conta.sacar(valor)) {
                banco.atualizarSaldoNoBanco(conta);
                System.out.println("Saque realizado! Saldo atual: R$ " + conta.getSaldo());
            } else {
                System.out.println("Saldo insuficiente.");
                banco.registrarErro(numero, "SALDO_INSUFICIENTE", "Tentativa de saque de R$ " + valor + " negada.");
            }
        } else {
            System.out.println("Conta não encontrada.");
            banco.registrarErro(numero, "SAQUE_FALHOU", "Tentativa de saque em conta inexistente.");
        }
    }

    public static void realizarTransferencia() {
        System.out.print("Conta de origem: ");
        int de = scanner.nextInt();
        System.out.print("Conta de destino: ");
        int para = scanner.nextInt();
        System.out.print("Valor: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        Conta contaOrigem = banco.buscarConta(de);
        Conta contaDestino = banco.buscarConta(para);

        if (contaOrigem != null && contaDestino != null) {
            if (contaOrigem.transferir(valor, contaDestino)) {
                banco.atualizarSaldoNoBanco(contaOrigem);
                banco.atualizarSaldoNoBanco(contaDestino);
                System.out.println("Transferência realizada com sucesso!");
            } else {
                System.out.println("Falha: Saldo insuficiente na conta de origem.");
                banco.registrarErro(de, "TRANSFERENCIA_NEGADA", "Saldo insuficiente para enviar R$ " + valor + " para conta " + para);
            }
        } else {
            System.out.println("Uma das contas não existe.");
            banco.registrarErro(de, "TRANSFERENCIA_ERRO", "Transferência falhou: Conta origem " + de + " ou destino " + para + " não encontrada.");
        }
    }

    // Método auxiliar para imprimir a lista de contas
    public static void exibirListaContas() {
        List<Conta> contas = banco.listarContas();
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta registrada.");
        } else {
            System.out.println("\n--- LISTA DE CONTAS ---");
            for (Conta c : contas) {
                System.out.println(c);
            }
        }
    }

    public static void consultarConta() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = banco.buscarConta(numero);
        if (conta != null) {
            System.out.println("Detalhes da Conta: " + conta);
        } else {
            System.out.println("Conta não encontrada.");
            banco.registrarErro(numero, "CONSULTA_FALHOU", "Tentativa de consulta em conta inexistente.");
        }
    }

    // Método para exibir os logs salvos no banco
    public static void exibirLogsErros() {
        System.out.println("\n--- LOGS DE ERROS REGISTRADOS NO BANCO ---");
        String sql = "SELECT * FROM logs_erros ORDER BY data_hora DESC";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.printf("[%s] Conta: %d | Erro: %s | Mensagem: %s%n",
                        rs.getTimestamp("data_hora"),
                        rs.getInt("numero_conta"),
                        rs.getString("tipo_erro"),
                        rs.getString("mensagem"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar logs: " + e.getMessage());
        }
    }
}