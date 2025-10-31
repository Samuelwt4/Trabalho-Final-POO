public class UsuarioIndividual extends Usuario {
    public UsuarioIndividual(String nome, String email, int Id) {
        super(nome, email, Id);
    }
    public void exibirPermissoes() {
        System.out.println("Permissões: acesso aos próprios dados e contas.");
    }
}
