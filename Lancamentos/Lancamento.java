package Lancamentos;

import Usuarios.Usuario;
import ContasCarteiras.ContaFinanceira;

public class Lancamento {

    private int id;
    private int tipo;          // 1 = receita, 2 = despesa, 3 = transferência
    private double valor;
    private String categoria;
    private String subcategoria;
    private String data;       
    private int recorrencia;   // 0 = nenhuma, 1 = mensal, 2 = anual
    private Usuario pagador;
    private Usuario beneficiario;
    private String anexo;
    private ContaFinanceira contaOrigem;
    private ContaFinanceira contaDestino;
    private int numeroParcela;
    private int totalParcelas;
    private boolean estornado;

    public Lancamento(
            int id,
            int tipo,
            double valor,
            String categoria,
            String subcategoria,
            String data,
            int recorrencia,
            Usuario pagador,
            Usuario beneficiario,
            String anexo,
            ContaFinanceira contaOrigem,
            ContaFinanceira contaDestino
    ) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.data = data;
        this.recorrencia = recorrencia;
        this.pagador = pagador;
        this.beneficiario = beneficiario;
        this.anexo = anexo;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.numeroParcela = 1;
        this.totalParcelas = 1;
        this.estornado = false;
    }

    // get e set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(int recorrencia) {
        this.recorrencia = recorrencia;
    }

    public Usuario getPagador() {
        return pagador;
    }

    public void setPagador(Usuario pagador) {
        this.pagador = pagador;
    }

    public Usuario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Usuario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public ContaFinanceira getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(ContaFinanceira contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public ContaFinanceira getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(ContaFinanceira contaDestino) {
        this.contaDestino = contaDestino;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public int getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(int totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public boolean isEstornado() {
        return estornado;
    }

    public void setEstornado(boolean estornado) {
        this.estornado = estornado;
    }

    // métodos de apoio

    private String getTipoComoTexto() {
        if (tipo == 1) {
            return "Receita";
        } else if (tipo == 2) {
            return "Despesa";
        } else if (tipo == 3) {
            return "Transferência";
        } else {
            return "Desconhecido";
        }
    }

    private String getRecorrenciaComoTexto() {
        if (recorrencia == 0) {
            return "Nenhuma";
        } else if (recorrencia == 1) {
            return "Mensal";
        } else if (recorrencia == 2) {
            return "Anual";
        } else {
            return "Desconhecida";
        }
    }

    public boolean ehRecorrente() {
        return recorrencia != 0;
    }

    public boolean ehParcelado() {
        return totalParcelas > 1;
    }

    // exibir infos
    public void exibir() {
        System.out.println("ID do lançamento: " + id);
        System.out.println("Tipo: " + getTipoComoTexto());
        System.out.println("Valor: R$ " + valor);
        System.out.println("Categoria: " + categoria + " / " + subcategoria);
        System.out.println("Data: " + data);
        System.out.println("Recorrência: " + getRecorrenciaComoTexto());

        if (ehParcelado()) {
            System.out.println("Parcela: " + numeroParcela + " de " + totalParcelas);
        } else {
            System.out.println("Parcela: única");
        }

        System.out.println("Pagador: " + (pagador != null ? pagador.getNome() : "N/A"));
        System.out.println("Beneficiário: " + (beneficiario != null ? beneficiario.getNome() : "N/A"));
        System.out.println("Anexo (simulado): " + (anexo != null ? anexo : "nenhum"));

        System.out.println("Conta origem: " +
                (contaOrigem != null ? contaOrigem.getNome() : "nenhuma"));
        System.out.println("Conta destino: " +
                (contaDestino != null ? contaDestino.getNome() : "nenhuma"));

        System.out.println("Estornado: " + (estornado ? "SIM" : "NÃO"));
        System.out.println("--------------------------");
    }

    // Aplica o lançamento nas contas
    public void aplicar() {
        if (estornado) {
            System.out.println("Lançamento " + id + " já foi estornado. Não será aplicado novamente.");
            return;
        }

        if (contaOrigem == null && contaDestino == null) {
            System.out.println("Nenhuma conta associada ao lançamento " + id);
            return;
        }

        if (tipo == 1) {
            // RECEITA: entra dinheiro na conta de origem
            if (contaOrigem != null) {
                contaOrigem.depositar(valor);
            } else {
                System.out.println("Receita sem conta de origem para receber o valor.");
            }
        } else if (tipo == 2) {
            // DESPESA: sai dinheiro da conta de origem
            if (contaOrigem != null) {
                // Validação de saldo é feita dentro de sacar()
                contaOrigem.sacar(valor);
            } else {
                System.out.println("Despesa sem conta de origem para pagar.");
            }
        } else if (tipo == 3) {
            // TRANSFERÊNCIA: sai da origem e entra na destino
            if (contaOrigem != null && contaDestino != null) {
                contaOrigem.sacar(valor);
                contaDestino.depositar(valor);
            } else {
                System.out.println("Transferência precisa de conta de origem e destino.");
            }
        } else {
            System.out.println("Tipo de lançamento inválido: " + tipo);
        }
    }

    // Estorna o lançamento
    public void estornar() {
        if (estornado) {
            System.out.println("Lançamento " + id + " já está estornado.");
            return;
        }

        if (contaOrigem == null && contaDestino == null) {
            System.out.println("Nenhuma conta associada ao lançamento " + id + " para estornar.");
            return;
        }

        System.out.println("Estornando lançamento ID " + id + "...");

        if (tipo == 1) {
            // Estorno de RECEITA: retirar o valor da conta de origem
            if (contaOrigem != null) {
                contaOrigem.sacar(valor);
            } else {
                System.out.println("Não há conta de origem para estornar esta receita.");
            }
        } else if (tipo == 2) {
            // Estorno de DESPESA: devolver o valor para a conta de origem
            if (contaOrigem != null) {
                contaOrigem.depositar(valor);
            } else {
                System.out.println("Não há conta de origem para estornar esta despesa.");
            }
        } else if (tipo == 3) {
            // Estorno de transferencia
            if (contaOrigem != null && contaDestino != null) {
                contaDestino.sacar(valor);
                contaOrigem.depositar(valor);
            } else {
                System.out.println("Transferência sem contas de origem e destino para estornar.");
            }
        } else {
            System.out.println("Tipo de lançamento inválido para estorno: " + tipo);
        }

        estornado = true;

        System.out.println("Estorno concluído para o lançamento ID " + id + ".");
        System.out.println("--------------------------");
    }
}
