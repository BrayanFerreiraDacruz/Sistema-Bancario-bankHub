package endereço;

public class cepcliente {

    private  String endereco;
    private  String cpfformatado;
    private String nome;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public void Cliente(String nome, String cpfformatado, String endereco) {
        if (cpfformatado.length() != 11) {
            throw new IllegalArgumentException("O CPF deve ter 11 dígitos");
        }
        this.nome = nome;
        this.cpfformatado = cpfformatado;
        this.endereco = endereco;
    }
    @Override
    public String toString() {
        return logradouro + ", " + bairro + " - " + localidade + "/" + uf;
    }
}