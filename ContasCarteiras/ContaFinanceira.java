package ContasCarteiras;
import Usuarios.Usuario;

public abstract class ContaFinanceira {
    private int id;
    private String nome;
    private Usuario dono;
    protected double saldo;

    public ContaFinanceira(int id, String nome, Usuario dono, double saldoInicial){
        this.id = id;
        this.nome = nome;
        this.dono = dono;
        this.saldo = saldoInicial;
    }
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

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public double getSaldo() {
        return saldo;
    }
        // sem setSaldo pra não deixar mudar direto

    // depósito
    public void depositar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de depósito inválido!");
        } else {
            saldo += valor;
        }
    }

     // saque
    public void sacar(double valor) {
        if (valor <= 0) {
            System.out.println("Valor de saque inválido!");
        } else if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque!");
        } else {
            saldo -= valor;
        }
    }

    public abstract String getTipoConta();

    // exibir infos
    public void exibir() {
        System.out.println("ID da conta: " + id);
        System.out.println("Nome da conta: " + nome);
        System.out.println("Dono: " + (dono != null ? dono.getNome() : "sem dono"));
        System.out.println("Tipo de conta: " + getTipoConta());
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("--------------------------");
    }
}
