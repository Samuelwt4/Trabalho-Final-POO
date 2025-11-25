package ContasCarteiras;
import Usuarios.Usuario;

public class ContaDigital extends ContaFinanceira {

    public ContaDigital(int id, String nome, Usuario dono, double saldoInicial) {
        super(id, nome, dono, saldoInicial);
    }

    @Override
    public String getTipoConta() {
        return "Conta Digital";
    }
}
