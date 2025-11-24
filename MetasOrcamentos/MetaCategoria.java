package MetasOrcamentos;

public class MetaCategoria {

    private int id;
    private String categoria;     
    private double valorMeta;      
    private String prazo;        
    private double valorAcumulado;
    private int diasPassados;      
    private int diasTotais;        

    public MetaCategoria(int id, String categoria, double valorMeta, String prazo, int diasTotais) {
        this.id = id;
        this.categoria = categoria;
        this.valorMeta = valorMeta;
        this.prazo = prazo;
        this.valorAcumulado = 0.0;
        this.diasPassados = 0;
        this.diasTotais = diasTotais;
    }

    // get e set

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(double valorMeta) {
        this.valorMeta = valorMeta;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public double getValorAcumulado() {
        return valorAcumulado;
    }

    public int getDiasPassados() {
        return diasPassados;
    }

    public void setDiasPassados(int diasPassados) {
        this.diasPassados = diasPassados;
    }

    public int getDiasTotais() {
        return diasTotais;
    }

    public void setDiasTotais(int diasTotais) {
        this.diasTotais = diasTotais;
    }

    // Registrar gasto
    public void registrarGasto(double valor) {
        if (valor > 0) {
            valorAcumulado = valorAcumulado + valor;
        }
    }

    // Percentual da meta já usado
    public double calcularPercentualUsado() {
        if (valorMeta <= 0) {
            return 0.0;
        }
        double percentual = (valorAcumulado / valorMeta) * 100.0;
        return percentual;
    }

    // previsão gasto
    public double preverGastoTotal() {
        if (diasPassados <= 0) {
            return 0.0;
        }
        double mediaDiaria = valorAcumulado / diasPassados;
        double previsao = mediaDiaria * diasTotais;
        return previsao;
    }

    // exibir infos
    public void exibir() {
        System.out.println("ID da meta: " + id);
        System.out.println("Categoria: " + categoria);
        System.out.println("Valor da meta: R$ " + valorMeta);
        System.out.println("Prazo: " + prazo);
        System.out.println("Valor já gasto: R$ " + valorAcumulado);
        System.out.println("Dias passados: " + diasPassados + " / " + diasTotais);
        System.out.println("Percentual usado: " + calcularPercentualUsado() + "%");
        System.out.println("Previsão de gasto total: R$ " + preverGastoTotal());
        System.out.println("--------------------------");
    }

    // alerta de estouro de categoria
    public void verificarAlerta() {
        double percentual = calcularPercentualUsado();
        double previsao = preverGastoTotal();

        System.out.println("Verificando meta da categoria: " + categoria);

        // Já passou da meta
        if (valorMeta > 0 && valorAcumulado > valorMeta) {
            System.out.println("ALERTA: Meta JÁ ESTOUROU! Valor gasto: R$ " +
                    valorAcumulado + " / Meta: R$ " + valorMeta);
        }
        // Ainda não passou, mas com previsão
        else if (valorMeta > 0 && previsao > valorMeta) {
            System.out.println("ALERTA: Se continuar nesse ritmo, você deve estourar a meta.");
            System.out.println("Previsão de gasto total: R$ " + previsao +
                    " / Meta: R$ " + valorMeta);
        }
        // Dentro da meta
        else {
            System.out.println("Tudo certo: ainda dentro da meta.");
        }

        System.out.println("Percentual usado até agora: " + percentual + "%");
        System.out.println("--------------------------");
    }
}
