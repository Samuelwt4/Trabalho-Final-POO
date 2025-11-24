package ContasCarteiras;
import Usuarios.Usuario;

public class ContaCorrente extends ContaFinanceira {

    public ContaCorrente(int id, String nome, Usuario dono, double saldoInicial) {
        super(id, nome, dono, saldoInicial);
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
}
