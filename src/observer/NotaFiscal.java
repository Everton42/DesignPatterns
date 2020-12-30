package observer;

import java.util.Calendar;
import java.util.List;

public class NotaFiscal {

    private String razaoSocial;
    private String cnpj;
    private Calendar dataDeEmissao;
    private double valorBruto;
    private double impostos;
    public List<ItemDaNota> itens;
    public String observacoes;

    public NotaFiscal(String razaoSocial, String cnpj, Calendar dataDeEmissao, double valorBruto, double impostos,
            List<ItemDaNota> itens, String observacoes) {
        this.setRazaoSocial(razaoSocial);
        this.setCnpj(cnpj);
        this.setDataDeEmissao(dataDeEmissao);
        this.setValorBruto(valorBruto);
        this.setImpostos(impostos);
        this.itens = itens;
        this.observacoes = observacoes;
    }

    public double getImpostos() {
        return impostos;
    }

    public void setImpostos(double impostos) {
        this.impostos = impostos;
    }

    public double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Calendar getDataDeEmissao() {
        return dataDeEmissao;
    }

    public void setDataDeEmissao(Calendar dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}

class ItemDaNota {
    private String descricao;
    private double valor;

    public ItemDaNota(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
