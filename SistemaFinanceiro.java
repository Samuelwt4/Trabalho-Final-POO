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

        ContaFinanceira conta = null;

        if (tipo == 1 || tipo == 2 || tipo == 4 || tipo == 5) {
            System.out.print("Saldo inicial: ");
            double saldoInicial = Double.parseDouble(entrada.nextLine());

            if (tipo == 1) {
                conta = new ContaCorrente(idConta, nomeConta, dono, saldoInicial);
            } else if (tipo == 2) {
                conta = new ContaDigital(idConta, nomeConta, dono, saldoInicial);
            } else if (tipo == 4) {
                conta = new CarteiraInvestimento(idConta, nomeConta, dono, saldoInicial);
            } else if (tipo == 5) {
                conta = new CofrinhoVirtual(idConta, nomeConta, dono, saldoInicial);
            }
        } else if (tipo == 3) {
            System.out.print("Limite do cartão: ");
            double limite = Double.parseDouble(entrada.nextLine());
            conta = new CartaoCredito(idConta, nomeConta, dono, limite);
        } else {
            System.out.println("Tipo de conta inválido.");
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
            System.out.println("1 - Registrar lançamento");
            System.out.println("2 - Listar lançamentos");
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
}
