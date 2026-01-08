package Contas;

public class Conta {
    private int numero;
    Cliente cliente;
    private double saldo;
    public Conta(int numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0.00;
    }
    public int getNumero() {
        return numero;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public double getSaldo() {
        return saldo;
    }
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }
    public boolean transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            contaDestino.depositar(valor);
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "Número: " + numero + " | Saldo: " + saldo + "\nTitular: " + cliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
