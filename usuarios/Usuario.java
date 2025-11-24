package Usuarios;

public abstract class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    //get e set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // tipo de usuário
    public abstract String getTipo();

    //exibir infos
    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
    //  System.out.println("Senha: " + senha);
        System.out.println("Tipo: " + getTipo()); 
        System.out.println("--------------------------");
    }

}
