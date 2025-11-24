package ContasCarteiras;

import Usuarios.Usuario;

public class FabricaContas {

    // tipo:
    // 1 = Conta Corrente
    // 2 = Conta Digital
    // 3 = Cartão de Crédito
    // 4 = Carteira de Investimento
    // 5 = Cofrinho / Poupança virtual
    //
    // valor:
    // - para tipos 1,2,4,5 = saldo inicial
    // - para tipo 3 = limite do cartão
    public static ContaFinanceira criarConta(int tipo,
                                             int idConta,
                                             String nomeConta,
                                             Usuario dono,
                                             double valor) {

        if (tipo == 1) {
            return new ContaCorrente(idConta, nomeConta, dono, valor);
        } else if (tipo == 2) {
            return new ContaDigital(idConta, nomeConta, dono, valor);
        } else if (tipo == 3) {
            // aqui valor representa o limite
            return new CartaoCredito(idConta, nomeConta, dono, valor);
        } else if (tipo == 4) {
            return new CarteiraInvestimento(idConta, nomeConta, dono, valor);
        } else if (tipo == 5) {
            return new CofrinhoVirtual(idConta, nomeConta, dono, valor);
        }

        // tipo inválido
        return null;
    }
}
