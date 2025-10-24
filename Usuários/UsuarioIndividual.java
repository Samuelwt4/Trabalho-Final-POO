public class UsuarioIndividual extends Usuario {
    public UsuarioIndividual(String nome, String email) {
        super(nome, email);
    }
    public void exibirPermissoes() {
        System.out.println("Permissões: acesso aos próprios dados e contas.");
    }
}
