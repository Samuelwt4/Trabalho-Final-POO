package usuarios;

public class UsuarioIndividual extends Usuario {
    private String cpf;

    public UsuarioIndividual(int id, String nome, String email, String senha, String cpf){
        super(id, nome, email, senha);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getTipo(){
        return "Usuário Individual";
    }
}
