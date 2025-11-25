package ContasCarteiras;

import Exceptions.SaldoInsuficienteException;
import Exceptions.ValorInvalidoException;
import Usuarios.Usuario;

public abstract class ContaFinanceira {
    private int id;
    private String nome;
    private Usuario dono;
    protected double saldo;

    public ContaFinanceira(int id, String nome, Usuario dono, double saldoInicial) {
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

    // sem setSaldo para não deixar mudar diretamente

    // Método de depósito com validação
    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de depósito inválido! O valor deve ser maior que zero.");
        }
        saldo += valor;
    }

    // Método de saque com validação
    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {
        if (valor <= 0) {
            throw new ValorInvalidoException("Valor de saque inválido! O valor deve ser maior que zero.");
        } else if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar o saque.");
        }
        saldo -= valor;
    }

    public abstract String getTipoConta();

    // Exibir informações da conta
    public void exibir() {
        System.out.println("ID da conta: " + id);
        System.out.println("Nome da conta: " + nome);
        System.out.println("Dono: " + (dono != null ? dono.getNome() : "sem dono"));
        System.out.println("Tipo de conta: " + getTipoConta());
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("--------------------------");
    }
}
