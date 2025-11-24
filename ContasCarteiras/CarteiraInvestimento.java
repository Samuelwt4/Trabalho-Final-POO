package ContasCarteiras;
import usuarios.Usuario;

public class CarteiraInvestimento extends ContaFinanceira {

    public CarteiraInvestimento(int id, String nome, Usuario dono, double saldoInicial) {
        super(id, nome, dono, saldoInicial);
    }

    @Override
    public String getTipoConta() {
        return "Carteira de Investimento";
    }
}
