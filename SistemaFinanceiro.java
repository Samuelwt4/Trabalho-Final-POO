import java.util.ArrayList;
import java.util.Scanner;
import Usuarios.Usuario;
import Usuarios.UsuarioIndividual;
import Usuarios.Grupo;
import ContasCarteiras.ContaFinanceira;
import ContasCarteiras.ContaCorrente;
import ContasCarteiras.ContaDigital;
import ContasCarteiras.CartaoCredito;
import ContasCarteiras.CarteiraInvestimento;
import ContasCarteiras.CofrinhoVirtual;
import ContasCarteiras.FabricaContas;
import Lancamentos.Lancamento;
import MetasOrcamentos.MetaCategoria;
import Algoritmos.AlgoritmosFinanceiros;
public class SistemaFinanceiro {

    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private ArrayList<ContaFinanceira> contas = new ArrayList<ContaFinanceira>();
    private ArrayList<Lancamento> lancamentos = new ArrayList<Lancamento>();
    private ArrayList<MetaCategoria> metas = new ArrayList<MetaCategoria>();

    private Scanner entrada = new Scanner(System.in);

    // =========================
    // MENU PRINCIPAL
    // =========================
    public void executar() {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Usuários");
            System.out.println("2 - Contas");
            System.out.println("3 - Lançamentos");
            System.out.println("4 - Metas e Orçamentos");
            System.out.println("5 - Algoritmos Inteligentes");
            System.out.println("6 - Relatórios");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                menuUsuarios();
            } else if (opcao == 2) {
                menuContas();
            } else if (opcao == 3) {
                menuLancamentos();
            } else if (opcao == 4) {
                menuMetas();
            } else if (opcao == 5) {
                menuAlgoritmos();
            } else if (opcao == 6) {
                menuRelatorios();
            } else if (opcao == 0) {
                System.out.println("Saindo do sistema...");
            } else {
                System.out.println("Opção inválida!");
            }
        }

        entrada.close();
    }

    // =========================
    // MENU DE USUÁRIOS
    // =========================
    private void menuUsuarios() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU DE USUÁRIOS ===");
            System.out.println("1 - Cadastrar usuário individual");
            System.out.println("2 - Cadastrar grupo");
            System.out.println("3 - Listar usuários");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                cadastrarUsuarioIndividual();
            } else if (opcao == 2) {
                cadastrarGrupo();
            } else if (opcao == 3) {
                listarUsuarios();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarUsuarioIndividual() {
        int id = usuarios.size() + 1;

        System.out.println("\n=== Cadastro de Usuário Individual ===");
        System.out.print("Nome: ");
        String nome = entrada.nextLine();

        System.out.print("Email: ");
        String email = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        System.out.print("CPF: ");
        String cpf = entrada.nextLine();

        UsuarioIndividual u = new UsuarioIndividual(id, nome, email, senha, cpf);
        usuarios.add(u);

        System.out.println("Usuário individual cadastrado com ID: " + id);
    }

    private void cadastrarGrupo() {
        int id = usuarios.size() + 1;

        System.out.println("\n=== Cadastro de Grupo ===");
        System.out.print("Nome do grupo: ");
        String nome = entrada.nextLine();

        System.out.print("Email do grupo: ");
        String email = entrada.nextLine();

        System.out.print("Senha do grupo: ");
        String senha = entrada.nextLine();

        Grupo g = new Grupo(id, nome, email, senha);
        usuarios.add(g);

        System.out.println("Grupo cadastrado com ID: " + id);
    }

    private void listarUsuarios() {
        System.out.println("\n=== Lista de Usuários ===");
        if (usuarios.size() == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        int i = 0;
        while (i < usuarios.size()) {
            Usuario u = usuarios.get(i);
            u.exibir();
            i = i + 1;
        }
    }

    private Usuario buscarUsuarioPorId(int id) {
        int i = 0;
        while (i < usuarios.size()) {
            Usuario u = usuarios.get(i);
            if (u.getId() == id) {
                return u;
            }
            i = i + 1;
        }
        return null;
    }

    // =========================
    // MENU DE CONTAS
    // =========================
    private void menuContas() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU DE CONTAS ===");
            System.out.println("1 - Cadastrar conta");
            System.out.println("2 - Listar contas");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                cadastrarConta();
            } else if (opcao == 2) {
                listarContas();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarConta() {
        if (usuarios.size() == 0) {
            System.out.println("Você precisa cadastrar usuários antes de criar contas.");
            return;
        }

        int idConta = contas.size() + 1;

        System.out.println("\n=== Cadastro de Conta ===");

        listarUsuarios();
        System.out.print("ID do dono da conta: ");
        int idDono = Integer.parseInt(entrada.nextLine());
        Usuario dono = buscarUsuarioPorId(idDono);

        if (dono == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Nome/apelido da conta: ");
        String nomeConta = entrada.nextLine();

        System.out.println("Tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Digital");
        System.out.println("3 - Cartão de Crédito");
        System.out.println("4 - Carteira de Investimento");
        System.out.println("5 - Cofrinho / Poupança virtual");
        System.out.print("Escolha o tipo: ");
        int tipo = Integer.parseInt(entrada.nextLine());

        double valor = 0.0;

        if (tipo == 1 || tipo == 2 || tipo == 4 || tipo == 5) {
            System.out.print("Saldo inicial: ");
            valor = Double.parseDouble(entrada.nextLine());
        } else if (tipo == 3) {
            System.out.print("Limite do cartão: ");
            valor = Double.parseDouble(entrada.nextLine());
        } else {
            System.out.println("Tipo de conta inválido.");
            return;
        }

        // Uso da FACTORY para criar a conta
        ContaFinanceira conta = FabricaContas.criarConta(tipo, idConta, nomeConta, dono, valor);

        if (conta == null) {
            System.out.println("Não foi possível criar a conta. Tipo inválido.");
            return;
        }

        contas.add(conta);
        System.out.println("Conta cadastrada com ID: " + idConta);
    }

    private void listarContas() {
        System.out.println("\n=== Lista de Contas ===");
        if (contas.size() == 0) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }

        int i = 0;
        while (i < contas.size()) {
            ContaFinanceira c = contas.get(i);
            c.exibir();
            i = i + 1;
        }
    }

    private ContaFinanceira buscarContaPorId(int id) {
        int i = 0;
        while (i < contas.size()) {
            ContaFinanceira c = contas.get(i);
            if (c.getId() == id) {
                return c;
            }
            i = i + 1;
        }
        return null;
    }

    // =========================
    // MENU DE LANÇAMENTOS
    // =========================
    private void menuLancamentos() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU DE LANÇAMENTOS ===");
            System.out.println("1 - Registrar lançamento simples");
            System.out.println("2 - Listar lançamentos");
            System.out.println("3 - Estornar lançamento");
            System.out.println("4 - Registrar despesa parcelada");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                registrarLancamento();
            } else if (opcao == 2) {
                listarLancamentos();
            } else if (opcao == 3) {
                estornarLancamento();
            } else if (opcao == 4) {
                registrarDespesaParcelada();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void registrarLancamento() {
        if (contas.size() == 0) {
            System.out.println("Você precisa cadastrar contas antes de registrar lançamentos.");
            return;
        }

        int idLanc = lancamentos.size() + 1;

        System.out.println("\n=== Registro de Lançamento ===");
        System.out.println("Tipo de lançamento:");
        System.out.println("1 - Receita");
        System.out.println("2 - Despesa");
        System.out.println("3 - Transferência");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(entrada.nextLine());

        System.out.print("Valor: ");
        double valor = Double.parseDouble(entrada.nextLine());

        System.out.print("Categoria: ");
        String categoria = entrada.nextLine();

        System.out.print("Subcategoria: ");
        String subcategoria = entrada.nextLine();

        System.out.print("Data (ex: 24/11/2025): ");
        String data = entrada.nextLine();

        System.out.println("Recorrência (0 = nenhuma, 1 = mensal, 2 = anual): ");
        int recorrencia = Integer.parseInt(entrada.nextLine());

        listarUsuarios();
        System.out.print("ID do pagador (0 para nenhum): ");
        int idPagador = Integer.parseInt(entrada.nextLine());
        Usuario pagador = null;
        if (idPagador != 0) {
            pagador = buscarUsuarioPorId(idPagador);
        }

        System.out.print("ID do beneficiário (0 para nenhum): ");
        int idBeneficiario = Integer.parseInt(entrada.nextLine());
        Usuario beneficiario = null;
        if (idBeneficiario != 0) {
            beneficiario = buscarUsuarioPorId(idBeneficiario);
        }

        System.out.print("Descrição do anexo (simulado, ex: nota.jpg): ");
        String anexo = entrada.nextLine();

        listarContas();
        System.out.print("ID da conta de origem (0 para nenhuma): ");
        int idContaOrigem = Integer.parseInt(entrada.nextLine());
        ContaFinanceira contaOrigem = null;
        if (idContaOrigem != 0) {
            contaOrigem = buscarContaPorId(idContaOrigem);
        }

        System.out.print("ID da conta de destino (0 para nenhuma): ");
        int idContaDestino = Integer.parseInt(entrada.nextLine());
        ContaFinanceira contaDestino = null;
        if (idContaDestino != 0) {
            contaDestino = buscarContaPorId(idContaDestino);
        }

        Lancamento lanc = new Lancamento(
                idLanc,
                tipo,
                valor,
                categoria,
                subcategoria,
                data,
                recorrencia,
                pagador,
                beneficiario,
                anexo,
                contaOrigem,
                contaDestino
        );

        lanc.aplicar();
        lancamentos.add(lanc);

        System.out.println("Lançamento registrado com ID: " + idLanc);
    }

    private void listarLancamentos() {
        System.out.println("\n=== Lista de Lançamentos ===");
        if (lancamentos.size() == 0) {
            System.out.println("Nenhum lançamento registrado.");
            return;
        }

        int i = 0;
        while (i < lancamentos.size()) {
            Lancamento l = lancamentos.get(i);
            l.exibir();
            i = i + 1;
        }
    }

    private Lancamento buscarLancamentoPorId(int id) {
        int i = 0;
        while (i < lancamentos.size()) {
            Lancamento l = lancamentos.get(i);
            if (l.getId() == id) {
                return l;
            }
            i = i + 1;
        }
        return null;
    }

    private void estornarLancamento() {
        if (lancamentos.size() == 0) {
            System.out.println("Nenhum lançamento registrado para estornar.");
            return;
        }

        System.out.println("\n=== Estorno de Lançamento ===");
        System.out.println("Lançamentos existentes:");

        int i = 0;
        while (i < lancamentos.size()) {
            Lancamento l = lancamentos.get(i);
            System.out.println("ID: " + l.getId() +
                    " | Tipo: " + l.getTipo() +
                    " | Valor: R$ " + l.getValor());
            i = i + 1;
        }

        System.out.print("Informe o ID do lançamento que deseja estornar: ");
        String linha = entrada.nextLine();
        if (linha.length() == 0) {
            System.out.println("ID inválido.");
            return;
        }

        int idLanc = Integer.parseInt(linha);
        Lancamento lanc = buscarLancamentoPorId(idLanc);

        if (lanc == null) {
            System.out.println("Lançamento não encontrado.");
            return;
        }

        lanc.estornar();
    }

    private void registrarDespesaParcelada() {
        if (contas.size() == 0) {
            System.out.println("Você precisa cadastrar contas antes de registrar lançamentos.");
            return;
        }

        System.out.println("\n=== Registro de Despesa Parcelada ===");

        // Conta que vai pagar a despesa
        listarContas();
        System.out.print("ID da conta de origem (que vai pagar as parcelas): ");
        int idContaOrigem = Integer.parseInt(entrada.nextLine());
        ContaFinanceira contaOrigem = buscarContaPorId(idContaOrigem);

        if (contaOrigem == null) {
            System.out.println("Conta de origem não encontrada.");
            return;
        }

        System.out.print("Valor TOTAL da despesa (somando todas as parcelas): ");
        double valorTotal = Double.parseDouble(entrada.nextLine());

        System.out.print("Quantidade de parcelas: ");
        int qtdParcelas = Integer.parseInt(entrada.nextLine());

        if (qtdParcelas <= 0) {
            System.out.println("Quantidade de parcelas inválida.");
            return;
        }

        double valorParcela = valorTotal / qtdParcelas;

        System.out.print("Categoria: ");
        String categoria = entrada.nextLine();

        System.out.print("Subcategoria: ");
        String subcategoria = entrada.nextLine();

        System.out.print("Data base (ex: 24/11/2025): ");
        String dataBase = entrada.nextLine();

        System.out.println("Recorrência (0 = nenhuma, 1 = mensal, 2 = anual): ");
        int recorrencia = Integer.parseInt(entrada.nextLine());

        // Pagador / Beneficiário
        listarUsuarios();
        System.out.print("ID do pagador (0 para nenhum): ");
        int idPagador = Integer.parseInt(entrada.nextLine());
        Usuario pagador = null;
        if (idPagador != 0) {
            pagador = buscarUsuarioPorId(idPagador);
        }

        System.out.print("ID do beneficiário (0 para nenhum): ");
        int idBeneficiario = Integer.parseInt(entrada.nextLine());
        Usuario beneficiario = null;
        if (idBeneficiario != 0) {
            beneficiario = buscarUsuarioPorId(idBeneficiario);
        }

        System.out.print("Descrição do anexo (simulado, ex: nota.jpg): ");
        String anexo = entrada.nextLine();

        // Geralmente não há conta destino específica para despesa parcelada
        ContaFinanceira contaDestino = null;

        int parcela = 1;
        while (parcela <= qtdParcelas) {
            int novoId = lancamentos.size() + 1;

            // Só para visualização, colocamos a parcela no texto da data
            String dataParcela = dataBase + " (parcela " + parcela + "/" + qtdParcelas + ")";

            Lancamento lanc = new Lancamento(
                    novoId,
                    2, // tipo 2 = DESPESA
                    valorParcela,
                    categoria,
                    subcategoria,
                    dataParcela,
                    recorrencia,
                    pagador,
                    beneficiario,
                    anexo,
                    contaOrigem,
                    contaDestino
            );

            lanc.setNumeroParcela(parcela);
            lanc.setTotalParcelas(qtdParcelas);

            // Aplica a parcela na conta (vai debitar)
            lanc.aplicar();

            // Guarda o lançamento na lista
            lancamentos.add(lanc);

            parcela = parcela + 1;
        }

        System.out.println("Despesa parcelada registrada com " + qtdParcelas +
                " parcelas de R$ " + valorParcela);
    }

    // =========================
    // MENU DE METAS
    // =========================
    private void menuMetas() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU DE METAS E ORÇAMENTOS ===");
            System.out.println("1 - Cadastrar meta por categoria");
            System.out.println("2 - Listar metas e verificar alertas");
            System.out.println("3 - Registrar gasto em uma meta");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                cadastrarMeta();
            } else if (opcao == 2) {
                listarMetasEAlertas();
            } else if (opcao == 3) {
                registrarGastoEmMeta();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarMeta() {
        int idMeta = metas.size() + 1;

        System.out.println("\n=== Cadastro de Meta por Categoria ===");
        System.out.print("Categoria (ex: Alimentação): ");
        String categoria = entrada.nextLine();

        System.out.print("Valor da meta (ex: 500.0): ");
        double valorMeta = Double.parseDouble(entrada.nextLine());

        System.out.print("Prazo (ex: Novembro 2025): ");
        String prazo = entrada.nextLine();

        System.out.print("Quantidade de dias do período (ex: 30): ");
        int diasTotais = Integer.parseInt(entrada.nextLine());

        MetaCategoria meta = new MetaCategoria(idMeta, categoria, valorMeta, prazo, diasTotais);
        metas.add(meta);

        System.out.println("Meta cadastrada com ID: " + idMeta);
    }

    private void listarMetasEAlertas() {
        System.out.println("\n=== Metas Cadastradas ===");
        if (metas.size() == 0) {
            System.out.println("Nenhuma meta cadastrada.");
            return;
        }

        int i = 0;
        while (i < metas.size()) {
            MetaCategoria meta = metas.get(i);
            meta.exibir();
            meta.verificarAlerta();
            i = i + 1;
        }
    }

    private MetaCategoria buscarMetaPorId(int id) {
        int i = 0;
        while (i < metas.size()) {
            MetaCategoria m = metas.get(i);
            if (m.getId() == id) {
                return m;
            }
            i = i + 1;
        }
        return null;
    }

    private void registrarGastoEmMeta() {
        if (metas.size() == 0) {
            System.out.println("Nenhuma meta cadastrada.");
            return;
        }

        System.out.println("\n=== Registrar Gasto em Meta ===");
        int i = 0;
        while (i < metas.size()) {
            MetaCategoria m = metas.get(i);
            System.out.println("ID: " + m.getId() + " | Categoria: " + m.getCategoria());
            i = i + 1;
        }

        System.out.print("Informe o ID da meta: ");
        int idMeta = Integer.parseInt(entrada.nextLine());
        MetaCategoria meta = buscarMetaPorId(idMeta);

        if (meta == null) {
            System.out.println("Meta não encontrada.");
            return;
        }

        System.out.print("Valor do gasto a registrar: ");
        double valor = Double.parseDouble(entrada.nextLine());
        meta.registrarGasto(valor);

        System.out.print("Quantos dias já se passaram no período dessa meta? ");
        int diasPassados = Integer.parseInt(entrada.nextLine());
        meta.setDiasPassados(diasPassados);

        meta.exibir();
        meta.verificarAlerta();
    }

    // =========================
    // MENU DE ALGORITMOS INTELIGENTES
    // =========================
    private void menuAlgoritmos() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== Algoritmos Inteligentes ===");
            System.out.println("1 - Projeção de saldo futuro");
            System.out.println("2 - Sugestão de economia por categoria (usando meta)");
            System.out.println("3 - Rateio de despesa com pesos");
            System.out.println("4 - Simulação de cenário de gasto");
            System.out.println("5 - Detecção de gastos fora do padrão");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                executarProjecaoSaldo();
            } else if (opcao == 2) {
                executarSugestaoEconomia();
            } else if (opcao == 3) {
                executarRateioComPesos();
            } else if (opcao == 4) {
                executarSimulacaoCenario();
            } else if (opcao == 5) {
                executarDeteccaoGastos();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void executarProjecaoSaldo() {
        System.out.println("\n=== Projeção de Saldo Futuro ===");
        System.out.print("Saldo atual: ");
        double saldoAtual = Double.parseDouble(entrada.nextLine());

        System.out.print("Média de receitas mensais: ");
        double mediaReceitas = Double.parseDouble(entrada.nextLine());

        System.out.print("Média de despesas mensais: ");
        double mediaDespesas = Double.parseDouble(entrada.nextLine());

        System.out.print("Quantidade de meses para projetar: ");
        int meses = Integer.parseInt(entrada.nextLine());

        double saldoFuturo = AlgoritmosFinanceiros.projetarSaldoFuturo(
                saldoAtual, mediaReceitas, mediaDespesas, meses
        );

        System.out.println("Saldo projetado para " + meses + " meses: R$ " + saldoFuturo);
    }

    private void executarSugestaoEconomia() {
        if (metas.size() == 0) {
            System.out.println("Não há metas cadastradas para analisar.");
            return;
        }

        System.out.println("\n=== Sugestão de Economia por Categoria ===");
        int i = 0;
        while (i < metas.size()) {
            MetaCategoria m = metas.get(i);
            System.out.println("ID: " + m.getId() + " | Categoria: " + m.getCategoria());
            i = i + 1;
        }

        System.out.print("Informe o ID da meta que deseja analisar: ");
        int idMeta = Integer.parseInt(entrada.nextLine());
        MetaCategoria meta = buscarMetaPorId(idMeta);

        if (meta == null) {
            System.out.println("Meta não encontrada.");
            return;
        }

        AlgoritmosFinanceiros.sugerirEconomiaPorCategoria(meta);
    }

    private void executarRateioComPesos() {
        System.out.println("\n=== Rateio de Despesa com Pesos ===");

        System.out.print("Valor total da despesa: ");
        double valorTotal = Double.parseDouble(entrada.nextLine());

        System.out.print("Quantidade de pessoas: ");
        int qtd = Integer.parseInt(entrada.nextLine());

        if (qtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        String[] nomes = new String[qtd];
        double[] pesos = new double[qtd];

        int i = 0;
        while (i < qtd) {
            System.out.print("Nome da pessoa " + (i + 1) + ": ");
            nomes[i] = entrada.nextLine();

            System.out.print("Peso da pessoa " + (i + 1) + " (ex: 1, 2, 0.5): ");
            pesos[i] = Double.parseDouble(entrada.nextLine());

            i = i + 1;
        }

        AlgoritmosFinanceiros.mostrarRateioComPesos(valorTotal, nomes, pesos);
    }

    private void executarSimulacaoCenario() {
        System.out.println("\n=== Simulação de Cenário de Gasto ===");
        System.out.print("Saldo atual: ");
        double saldoAtual = Double.parseDouble(entrada.nextLine());

        System.out.print("Gasto atual na categoria: ");
        double gastoAtual = Double.parseDouble(entrada.nextLine());

        System.out.print("Variação de gasto (negativo = gastar menos, positivo = gastar mais): ");
        double variacao = Double.parseDouble(entrada.nextLine());

        AlgoritmosFinanceiros.simularCenarioGasto(saldoAtual, gastoAtual, variacao);
    }

    private void executarDeteccaoGastos() {
        System.out.println("\n=== Detecção de Gastos Fora do Padrão ===");
        System.out.print("Quantidade de gastos para informar: ");
        int qtd = Integer.parseInt(entrada.nextLine());

        if (qtd <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        double[] gastos = new double[qtd];
        int i = 0;

        while (i < qtd) {
            System.out.print("Valor do gasto " + (i + 1) + ": ");
            gastos[i] = Double.parseDouble(entrada.nextLine());
            i = i + 1;
        }

        System.out.print("Multiplicador do limite (ex: 2.0 = acima do dobro da média): ");
        double mult = Double.parseDouble(entrada.nextLine());

        AlgoritmosFinanceiros.detectarGastosForaDoPadrao(gastos, mult);
    }

    // =========================
    // MENU DE RELATÓRIOS
    // =========================
    private void menuRelatorios() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU DE RELATÓRIOS ===");
            System.out.println("1 - Gastos por categoria (despesas)");
            System.out.println("2 - Ranking de maiores despesas");
            System.out.println("3 - Resumo por usuário/grupo");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            String linha = entrada.nextLine();
            if (linha.length() == 0) {
                opcao = -1;
            } else {
                opcao = Integer.parseInt(linha);
            }

            if (opcao == 1) {
                relatorioGastosPorCategoria();
            } else if (opcao == 2) {
                relatorioRankingDespesas();
            } else if (opcao == 3) {
                relatorioResumoPorUsuario();
            } else if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    // 1) Gastos por categoria (somando despesas)
    private void relatorioGastosPorCategoria() {
        System.out.println("\n=== Relatório: Gastos por Categoria (somente DESPESAS) ===");

        if (lancamentos.size() == 0) {
            System.out.println("Nenhum lançamento registrado.");
            return;
        }

        ArrayList<String> categorias = new ArrayList<String>();
        ArrayList<Double> totais = new ArrayList<Double>();

        int i = 0;
        while (i < lancamentos.size()) {
            Lancamento l = lancamentos.get(i);

            if (l.getTipo() == 2) { // 2 = despesa
                String cat = l.getCategoria();
                double valor = l.getValor();

                int pos = -1;
                int j = 0;
                while (j < categorias.size()) {
                    if (categorias.get(j).equals(cat)) {
                        pos = j;
                        j = categorias.size(); // para sair do while
                    } else {
                        j = j + 1;
                    }
                }

                if (pos == -1) {
                    categorias.add(cat);
                    totais.add(valor);
                } else {
                    double somaAtual = totais.get(pos);
                    totais.set(pos, somaAtual + valor);
                }
            }

            i = i + 1;
        }

        if (categorias.size() == 0) {
            System.out.println("Não há despesas para mostrar.");
            return;
        }

        System.out.println("\nCategoria | Total gasto");
        System.out.println("-----------------------");

        i = 0;
        while (i < categorias.size()) {
            System.out.println(categorias.get(i) + " | R$ " + totais.get(i));
            i = i + 1;
        }
    }

    // 2) Ranking de maiores despesas
    private void relatorioRankingDespesas() {
        System.out.println("\n=== Relatório: Ranking de Maiores Despesas ===");

        ArrayList<Lancamento> despesas = new ArrayList<Lancamento>();

        int i = 0;
        while (i < lancamentos.size()) {
            Lancamento l = lancamentos.get(i);
            if (l.getTipo() == 2) { // 2 = despesa
                despesas.add(l);
            }
            i = i + 1;
        }

        if (despesas.size() == 0) {
            System.out.println("Nenhuma despesa registrada.");
            return;
        }

        // Ordenação simples (Selection Sort) por valor decrescente
        int n = despesas.size();
        int pos = 0;
        while (pos < n - 1) {
            int indiceMaior = pos;

            int j = pos + 1;
            while (j < n) {
                if (despesas.get(j).getValor() > despesas.get(indiceMaior).getValor()) {
                    indiceMaior = j;
                }
                j = j + 1;
            }

            if (indiceMaior != pos) {
                Lancamento temp = despesas.get(pos);
                despesas.set(pos, despesas.get(indiceMaior));
                despesas.set(indiceMaior, temp);
            }

            pos = pos + 1;
        }

        // Mostrar apenas as top 5 (ou menos se não tiver)
        int limite = 5;
        if (despesas.size() < 5) {
            limite = despesas.size();
        }

        System.out.println("Top " + limite + " maiores despesas:");
        int k = 0;
        while (k < limite) {
            Lancamento l = despesas.get(k);
            System.out.println((k + 1) + "º - ID " + l.getId() +
                    " | Categoria: " + l.getCategoria() +
                    " / " + l.getSubcategoria() +
                    " | Valor: R$ " + l.getValor() +
                    " | Data: " + l.getData());
            k = k + 1;
        }
    }

    // 3) Resumo por usuário/grupo
    private void relatorioResumoPorUsuario() {
        System.out.println("\n=== Relatório: Resumo por Usuário/Grupo ===");

        if (usuarios.size() == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        int i = 0;
        while (i < usuarios.size()) {
            Usuario u = usuarios.get(i);

            double totalPagou = 0.0;
            double totalRecebeu = 0.0;

            int j = 0;
            while (j < lancamentos.size()) {
                Lancamento l = lancamentos.get(j);

                Usuario pag = l.getPagador();
                if (pag != null && pag.getId() == u.getId()) {
                    totalPagou = totalPagou + l.getValor();
                }

                Usuario ben = l.getBeneficiario();
                if (ben != null && ben.getId() == u.getId()) {
                    totalRecebeu = totalRecebeu + l.getValor();
                }

                j = j + 1;
            }

            System.out.println("Usuário/Grupo: " + u.getNome() + " (ID: " + u.getId() + ")");
            System.out.println("  Total pago: R$ " + totalPagou);
            System.out.println("  Total recebido: R$ " + totalRecebeu);
            System.out.println("--------------------------");

            i = i + 1;
        }
    }
}
