import Contas.Conta;

class Emprestimo {
    private Conta conta;
    private double valor;
    private double taxaJuros;
    private int meses;
    private double saldoDevido;

    public Emprestimo(Conta conta, double valor, double taxaJuros, int meses) {
        this.conta = conta;
        this.valor = valor;
        this.taxaJuros = taxaJuros;
        this.meses = meses;
        this.saldoDevido = calcularSaldoDevido();
    }

    private double calcularSaldoDevido() {
        return valor * Math.pow((1 + taxaJuros / 100), meses);
    }

    public Conta getConta() {
        return conta;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldoDevido() {
        return saldoDevido;
    }

    public void pagarEmprestimo(double valorPagamento) {
        if (valorPagamento > 0 && valorPagamento <= saldoDevido) {
            saldoDevido -= valorPagamento;
            System.out.println("Pagamento realizado de R$ " + valorPagamento + ". Saldo devido atual: R$ " + saldoDevido);
        } else {
            System.out.println("Valor de pagamento inválido.");
        }
    }

    public boolean podePagar() {
        return saldoDevido <= conta.getSaldo();
    }

    @Override
    public String toString() {
        return "Empréstimo para a conta: " + conta.getNumero() + " | Valor: R$ " + valor + " | Saldo Devido: R$ " + saldoDevido + " | Prazo: " + meses + " meses";
    }
}
