package Usuarios;

import java.util.ArrayList;

public class Grupo extends Usuario {

    private ArrayList<UsuarioIndividual> membros;

    public Grupo(int id, String nome, String email, String senha) {
        super(id, nome, email, senha);
        this.membros = new ArrayList<UsuarioIndividual>();
    }

    public void adicionarMembro(UsuarioIndividual membro) {
        if (membro != null && !membros.contains(membro)) {
            membros.add(membro);
        }
    }

    public void removerMembro(UsuarioIndividual membro) {
        membros.remove(membro);
    }

    public ArrayList<UsuarioIndividual> getMembros() {
        return membros;
    }

    public void listarMembros() {
        System.out.println("Membros do grupo: " + getNome());
        for (UsuarioIndividual m : membros) {
            System.out.println("- " + m.getNome() + " (CPF: " + m.getCpf() + ")");
        }
        System.out.println("--------------------------");
    }

    @Override
    public String getTipo() {
        return "Grupo";
    }
}

