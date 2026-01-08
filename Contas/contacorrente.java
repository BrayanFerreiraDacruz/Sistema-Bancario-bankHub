package Contas;

public class contacorrente extends Conta {
    private double limiteChequeEspecial;

    public contacorrente(int numero, Cliente cliente, double limiteChequeEspecial, Cliente Cliente) {
        super(numero, Cliente);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido.");
            return false;
        }
        if (getSaldo() + limiteChequeEspecial >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
            return true;
        } else {
            System.out.println("Saldo insuficiente, incluindo limite do cheque especial.");
            return false;
        }
    }

    public void setSaldo(double v) {

    }
}