public abstract class Usuario {
    protected String nome;
    protected String email;
    protected int Id;

    protected Usuario(String nome, String email, int Id) {
        this.nome = nome;
        this.email = email;
        this.Id = Id;
    }

    public abstract void exibirPermissoes();

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getId(){
        return Id;
    }
}
