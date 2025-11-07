package Contas_e_Carteiras;

public class Cofrinho extends ContaFinanceira {
    public Cofrinho(double saldoInicial) {
        super("Cofrinho", saldoInicial);
    }

    @Override
    public void sacar(double valor) {
        System.out.println("Não é possível sacar diretamente do cofrinho!");
    }
}
