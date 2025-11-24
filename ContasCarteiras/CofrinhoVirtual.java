package ContasCarteiras;
import Usuarios.Usuario;

public class CofrinhoVirtual extends ContaFinanceira {

    public CofrinhoVirtual(int id, String nome, Usuario dono, double saldoInicial) {
        super(id, nome, dono, saldoInicial);
    }

    @Override
    public String getTipoConta() {
        return "Cofrinho Virtual";
    }
}