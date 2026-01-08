package Contas;

public class Endereco {
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;

    @Override
    public String toString() {
        return logradouro + ", " + bairro + " - " + localidade + "/" + uf;
    }
}