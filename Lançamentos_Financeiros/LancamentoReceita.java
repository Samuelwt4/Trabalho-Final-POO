package Lançamentos_Financeiros;

import contas.ContaFinanceira;
import java.time.LocalDate;

public class LancamentoReceita extends Lancamento {

    private ContaFinanceira contaDestino;

    public LancamentoReceita(String categoria, double valor, LocalDate data, ContaFinanceira contaDestino) {
        super(categoria, valor, data, TipoLancamento.RECEITA);
        this.contaDestino = contaDestino;
    }

    public void aplicar() {
        contaDestino.depositar(valor);
        System.out.println("Receita aplicada: +" + valor);
    }
}
