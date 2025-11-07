package Contas_e_Carteiras;

public abstract class ContaFinanceira {
    protected String nome;
    protected double saldo;

    public ContaFinanceira(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldo = saldoInicial;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de R$" + valor + " realizado em " + nome);
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado em " + nome);
        } else {
            System.out.println("Saldo insuficiente em " + nome);
        }
    }

    public void exibirSaldo() {
        System.out.println("Saldo da conta " + nome + ": R$" + saldo);
    }
}
