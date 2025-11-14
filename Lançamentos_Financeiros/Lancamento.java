package Lançamentos_Financeiros;

import java.time.LocalDate;

public abstract class Lancamento {
    
    protected String categoria;
    protected double valor;
    protected LocalDate data;
    protected TipoLancamento tipo;

    public Lancamento(String categoria, double valor, LocalDate data, TipoLancamento tipo) {
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getCategoria() {
        return categoria;
    }

    public abstract void aplicar();
}
