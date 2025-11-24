package Lancamentos;

import Usuarios.Usuario;
import ContasCarteiras.ContaFinanceira;

public class Lancamento {

    private int id;
    private int tipo; // 1 = receita, 2 = despesa, 3 = transferência
    private double valor;
    private String categoria;
    private String subcategoria;
    private String data;       
    private int recorrencia;   
    private Usuario pagador;
    private Usuario beneficiario;
    private String anexo;
    private ContaFinanceira contaOrigem;
    private ContaFinanceira contaDestino;
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

    public boolean isEstornado() {
        return estornado;
    }

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

    // exibir infos
    public void exibir() {
        System.out.println("ID do lançamento: " + id);
        System.out.println("Tipo: " + getTipoComoTexto());
        System.out.println("Valor: R$ " + valor);
        System.out.println("Categoria: " + categoria + " / " + subcategoria);
        System.out.println("Data: " + data);
        System.out.println("Recorrência: " + getRecorrenciaComoTexto());
        System.out.println("Pagador: " + (pagador != null ? pagador.getNome() : "N/A"));
        System.out.println("Beneficiário: " + (beneficiario != null ? beneficiario.getNome() : "N/A"));
        System.out.println("Anexo (simulado): " + (anexo != null ? anexo : "nenhum"));
        System.out.println("Conta origem: " +
                (contaOrigem != null ? contaOrigem.getNome() : "nenhuma"));
        System.out.println("Conta destino: " +
                (contaDestino != null ? contaDestino.getNome() : "nenhuma"));
        System.out.println("Estornado: " + (estornado ? "Sim" : "Não"));
        System.out.println("--------------------------");
    }

    // Aplica o lançamento nas contas
    public void aplicar() {
        if (contaOrigem == null && contaDestino == null) {
            System.out.println("Nenhuma conta associada ao lançamento " + id);
            return;
        }

        if (tipo == 1) {
            // receita
            if (contaOrigem != null) {
                contaOrigem.depositar(valor);
            }
        } else if (tipo == 2) {
            // despesa
            if (contaOrigem != null) {
                contaOrigem.sacar(valor);
            }
        } else if (tipo == 3) {
            // transferencia
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

    // regra de estorno
    public void estornar() {
        if (estornado) {
            System.out.println("Lançamento " + id + " já foi estornado.");
            return;
        }

        if (contaOrigem == null && contaDestino == null) {
            System.out.println("Não é possível estornar o lançamento " + id + " (sem contas associadas).");
            return;
        }

        // Desfaz o aplicar
        if (tipo == 1) {
            // Receita
            if (contaOrigem != null) {
                contaOrigem.sacar(valor);
            }
        } else if (tipo == 2) {
            // Despesa
            if (contaOrigem != null) {
                contaOrigem.depositar(valor);
            }
        } else if (tipo == 3) {
            // Transferência
            if (contaOrigem != null && contaDestino != null) {
                contaDestino.sacar(valor);
                contaOrigem.depositar(valor);
            }
        }

        estornado = true;
        System.out.println("Lançamento " + id + " estornado com sucesso.");
    }
    // regra parcelamento
    public void mostrarParcelas(int quantidadeParcelas) {
        if (quantidadeParcelas <= 1) {
            System.out.println("A quantidade de parcelas deve ser maior que 1.");
            return;
        }

        double valorParcela = valor / quantidadeParcelas;

        System.out.println("Parcelamento do lançamento " + id + " em " + quantidadeParcelas + " vezes:");
        int i = 1;
        while (i <= quantidadeParcelas) {
            System.out.println("Parcela " + i + ": R$ " + valorParcela);
            i = i + 1;
        }
        System.out.println("--------------------------");
    }

    // regra lançamento recorrente
    public Lancamento gerarProximoLancamento(int novoId, String novaData) {
        if (recorrencia == 0) {
            System.out.println("Lançamento " + id + " não é recorrente.");
            return null;
        }

        Lancamento proximo = new Lancamento(
                novoId,
                this.tipo,
                this.valor,
                this.categoria,
                this.subcategoria,
                novaData,  
                this.recorrencia,
                this.pagador,
                this.beneficiario,
                this.anexo,
                this.contaOrigem,
                this.contaDestino
        );

        return proximo;
    }

    // regra alerta de limite
    public void verificarLimiteSimples(double limite) {
        if (tipo == 2 && valor > limite) {
            System.out.println("ALERTA: Despesa do lançamento " + id +
                    " (R$ " + valor + ") ultrapassa o limite de R$ " + limite +
                    " na categoria " + categoria + ".");
        }
    }
    // regra rateio
    public void mostrarRateioSimples(int quantidadePessoas) {
        if (quantidadePessoas <= 0) {
            System.out.println("Quantidade de pessoas deve ser maior que zero.");
            return;
        }

        if (tipo != 2) {
            System.out.println("Rateio simples só faz sentido para despesas (tipo 2).");
            return;
        }

        double valorPorPessoa = valor / quantidadePessoas;

        System.out.println("Rateio do lançamento " + id + " entre " + quantidadePessoas + " pessoas:");
        System.out.println("Cada pessoa deve: R$ " + valorPorPessoa);
        System.out.println("--------------------------");
    }
}
