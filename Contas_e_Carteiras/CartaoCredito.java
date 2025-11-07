package Contas_e_Carteiras;

public class CartaoCredito extends ContaFinanceira {
    private double limite;

    public CartaoCredito(double limite) {
        super("Cartão de Crédito", 0);
        this.limite = limite;
    }

    public void sacar(double valor) {
        if (saldo + limite >= valor) {
            saldo -= valor;
            System.out.println("Compra de R$" + valor + " feita no cartão.");
        } else {
            System.out.println("Limite insuficiente!");
        }
    }

    public void exibirSaldo() {
        System.out.println("Limite disponível no cartão: R$" + (limite + saldo));
    }
}
