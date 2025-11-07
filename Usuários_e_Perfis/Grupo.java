package Usuários_e_Perfis;

import java.util.ArrayList;

public class Grupo extends Usuario {

    private ArrayList<UsuarioIndividual> membros = new ArrayList<>();

    public Grupo(String nome, String email, int Id) {
        super(nome, email, Id);
    }

    public void exibirPermissoes() {
        System.out.println("Permissões: pode gerenciar membros e contas do grupo.");
    }

    public void adicionarMembro(UsuarioIndividual usuario) {
        membros.add(usuario);
    }

    public void mostrarMembros() {
        System.out.println("Membros do grupo " + nome + ":");
        for (UsuarioIndividual u : membros) {
            System.out.println("- " + u.getNome());
        }
    }
}
