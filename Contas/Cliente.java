package Contas;

public class Cliente {

    private String cpfformatado;
    private String nome;
    private String cpf;
    private String endereco; // Guardará o texto retornado pela API

    public Cliente(String nome, String cpf, String endereco) {
        this.nome = nome;
        // Se o CPF vier nulo do banco, evitamos o erro atribuindo uma string vazia
        this.cpf = (cpf != null) ? cpf : "00000000000";
        this.endereco = endereco;
        this.cpfformatado = cpf.substring(0, 3) + "." +
                           cpf.substring(3, 6) + "." +
                           cpf.substring(6, 9) + "-" +
                           cpf.substring(9, 11);
        this.nome = nome;
        this.cpfformatado = cpfformatado;
        this.endereco = endereco;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }

    @Override
    public String toString() {
        return "Nome: " + nome +
                " | CPF: " + cpfformatado +
                "\nEndereço Completo: " + endereco; // Aqui aparecerá o que a API buscou
    }

    public String getCpfformatado() {
        return "";
    }

    public String getCpf() {
        return cpf;
    }
}
