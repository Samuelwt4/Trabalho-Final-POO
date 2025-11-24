package ContasCarteiras;
import Usuarios.Usuario;

public class CartaoCredito extends ContaFinanceira {

    private double limiteTotal;

    public CartaoCredito(int id, String nome, Usuario dono, double limiteTotal) {
        super(id, nome, dono, limiteTotal);
        this.limiteTotal = limiteTotal;
    }

    public double getLimiteTotal() {
        return limiteTotal;
    }

    @Override
    public String getTipoConta() {
        return "Cartão de Crédito";
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0) {
            if (saldo >= valor) {
                saldo = saldo - valor;
            } else {
                System.out.println("Limite do cartão insuficiente para essa compra.");
            }
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            double novoSaldo = saldo + valor;
            if (novoSaldo > limiteTotal) {
                saldo = limiteTotal;
            } else {
                saldo = novoSaldo;
            }
        }
    }

    @Override
    public void exibir() {
        System.out.println("ID da conta: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Dono: " + (getDono() != null ? getDono().getNome() : "N/A"));
        System.out.println("Tipo de conta: " + getTipoConta());
        System.out.println("Limite total: R$ " + limiteTotal);
        System.out.println("Limite disponível: R$ " + saldo);
        System.out.println("--------------------------");
    }
}
