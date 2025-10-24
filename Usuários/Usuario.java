public abstract class Usuario {
    protected String nome;
    protected String email;

    protected Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public abstract void exibirPermissoes();

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
