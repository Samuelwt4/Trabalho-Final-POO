package Algoritmos;
import MetasOrcamentos.MetaCategoria;

public class AlgoritmosFinanceiros {

    // PROJEÇÃO DE SALDO FUTURO
    // saldoAtual: quanto tem hoje
    // mediaReceitasMensais: quanto recebe por mês (média)
    // mediaDespesasMensais: quanto gasta por mês (média)
    // meses: em quantos meses quer projetar
    public static double projetarSaldoFuturo(double saldoAtual,
                                             double mediaReceitasMensais,
                                             double mediaDespesasMensais,
                                             int meses) {

        double saldoProjetado = saldoAtual;

        int i = 0;
        while (i < meses) {
            saldoProjetado = saldoProjetado + mediaReceitasMensais - mediaDespesasMensais;
            i = i + 1;
        }

        return saldoProjetado;
    }

    // 2SUGESTÃO DE ECONOMIA POR CATEGORIA
    // vê a previsão de gasto da meta
    // se previsão > valorMeta calcula quanto precisa reduzir por dia
    public static void sugerirEconomiaPorCategoria(MetaCategoria meta) {

        double valorMeta = meta.getValorMeta();
        double valorAtual = meta.getValorAcumulado();
        double previsao = meta.preverGastoTotal();
        int diasTotais = meta.getDiasTotais();
        int diasPassados = meta.getDiasPassados();

        System.out.println("Analisando meta da categoria: " + meta.getCategoria());
        System.out.println("Meta: R$ " + valorMeta + " | Já gasto: R$ " + valorAtual);
        System.out.println("Previsão de gasto total: R$ " + previsao);

        if (valorMeta <= 0) {
            System.out.println("Meta inválida (valor da meta menor ou igual a zero).");
            return;
        }

        if (previsao <= valorMeta) {
            System.out.println("Você está dentro da meta. Não é necessário economizar mais por enquanto.");
            System.out.println("--------------------------");
            return;
        }

        // Quanto está previsto de excesso
        double excesso = previsao - valorMeta;

        int diasRestantes = diasTotais - diasPassados;
        if (diasRestantes <= 0) {
            diasRestantes = 1;
        }

        double economiaPorDia = excesso / diasRestantes;

        System.out.println("ATENÇÃO: Se continuar nesse ritmo, você deve estourar a meta em R$ " + excesso);
        System.out.println("SUGESTÃO: Economizar cerca de R$ " + economiaPorDia +
                " por dia nos próximos " + diasRestantes + " dias.");
        System.out.println("--------------------------");
    }

    // RATEIO AUTOMÁTICO DE DESPESAS ENTRE PESSOAS
    // valorTotal: valor total da despesa
    // nomes: nomes das pessoas
    // pesos: "peso" de cada pessoa (quem paga mais recebe um peso maior)
    // Exemplo:
    //   nomes = ["João", "Maria", "Pedro"]
    //   pesos = [1, 2, 1] -> Maria paga o dobro de João e Pedro
    public static void mostrarRateioComPesos(double valorTotal, String[] nomes, double[] pesos) {

        if (nomes == null || pesos == null) {
            System.out.println("Listas de nomes ou pesos nulas.");
            return;
        }

        int tamanhoNomes = nomes.length;
        int tamanhoPesos = pesos.length;

        if (tamanhoNomes == 0 || tamanhoPesos == 0 || tamanhoNomes != tamanhoPesos) {
            System.out.println("Listas inválidas. Devem ter o mesmo tamanho e não podem ser vazias.");
            return;
        }

        // Soma total dos pesos
        double somaPesos = 0.0;
        int i = 0;
        while (i < tamanhoPesos) {
            somaPesos = somaPesos + pesos[i];
            i = i + 1;
        }

        if (somaPesos <= 0) {
            System.out.println("Soma dos pesos deve ser maior que zero.");
            return;
        }

        System.out.println("Rateio com pesos para valor total: R$ " + valorTotal);

        // Calcula quanto cada um paga
        i = 0;
        while (i < tamanhoNomes) {
            double proporcao = pesos[i] / somaPesos;
            double valorPessoa = valorTotal * proporcao;
            System.out.println(nomes[i] + " deve pagar: R$ " + valorPessoa);
            i = i + 1;
        }

        System.out.println("--------------------------");
    }

    // SIMULAÇÃO DE CENÁRIO:
    // "E se eu gastar X a menos/a mais nessa categoria?"
    // saldoAtual: quanto tem agora
    // gastoAtualCategoria: quanto está gastando na categoria
    // variacaoGasto: valor que quer mudar (negativo = gastar menos, positivo = gastar mais)
    public static void simularCenarioGasto(double saldoAtual,
                                           double gastoAtualCategoria,
                                           double variacaoGasto) {

        double novoGastoCategoria = gastoAtualCategoria + variacaoGasto;
        double novoSaldo = saldoAtual - novoGastoCategoria;

        System.out.println("Simulação de cenário:");
        System.out.println("Saldo atual: R$ " + saldoAtual);
        System.out.println("Gasto atual na categoria: R$ " + gastoAtualCategoria);

        if (variacaoGasto < 0) {
            System.out.println("Você quer gastar R$ " + (-variacaoGasto) + " A MENOS nessa categoria.");
        } else if (variacaoGasto > 0) {
            System.out.println("Você quer gastar R$ " + variacaoGasto + " A MAIS nessa categoria.");
        } else {
            System.out.println("Sem alteração no gasto da categoria.");
        }

        System.out.println("Novo gasto na categoria seria: R$ " + novoGastoCategoria);
        System.out.println("Novo saldo estimado seria: R$ " + novoSaldo);
        System.out.println("--------------------------");
    }

    //DETECÇÃO DE GASTOS FORA DO PADRÃO (bem simples)
    // gastos: lista de valores (por exemplo, gastos diários ou por compra)
    // limiteMultiplicador: por exemplo, 2.0 = "acima do dobro da média"
    public static void detectarGastosForaDoPadrao(double[] gastos, double limiteMultiplicador) {

        if (gastos == null || gastos.length == 0) {
            System.out.println("Lista de gastos vazia.");
            return;
        }

        // Calcula a média
        double soma = 0.0;
        int i = 0;
        while (i < gastos.length) {
            soma = soma + gastos[i];
            i = i + 1;
        }

        double media = soma / gastos.length;

        double limiteSuperior = media * limiteMultiplicador;

        System.out.println("Média de gastos: R$ " + media);
        System.out.println("Qualquer gasto acima de R$ " + limiteSuperior +
                " será considerado fora do padrão.");
        System.out.println("Gastos fora do padrão:");

        // Verifica quem passou do limite
        i = 0;
        boolean encontrou = false;
        while (i < gastos.length) {
            if (gastos[i] > limiteSuperior) {
                System.out.println(" - Gasto " + i + ": R$ " + gastos[i]);
                encontrou = true;
            }
            i = i + 1;
        }

        if (!encontrou) {
            System.out.println("Nenhum gasto fora do padrão encontrado.");
        }

        System.out.println("--------------------------");
    }
}
