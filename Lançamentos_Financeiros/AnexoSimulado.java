package Lançamentos_Financeiros;

public class AnexoSimulado {

    private String nomeArquivo;
    private String tipoArquivo; 

    public AnexoSimulado(String nomeArquivo, String tipoArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.tipoArquivo = tipoArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    @Override
    public String toString() {
        return nomeArquivo + " (" + tipoArquivo + ")";
    }
}
