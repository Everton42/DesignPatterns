package builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Builder {
    public static void main(String[] args) {
        NotaFiscal notaFiscal = new NotaFiscalBuilder().paraEmpresa("razaoSocial").comCnpj("1805689665288").build();
        System.out.println(notaFiscal.getRazaoSocial());
        System.out.println(notaFiscal.getCnpj());
        System.out.println(notaFiscal.getDataDeEmissao().get(Calendar.DATE));
    }
}

class NotaFiscalBuilder {

    private String razaoSocial;
    private String cnpj;
    private List<ItemDaNota> todosItens = new ArrayList<ItemDaNota>();
    private double valorBruto;
    private double impostos;
    private String observacoes;
    private Calendar data;

    public NotaFiscalBuilder() {
        this.data = Calendar.getInstance();
    }

    public NotaFiscalBuilder paraEmpresa(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public NotaFiscalBuilder comCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public NotaFiscalBuilder com(ItemDaNota item) {
        todosItens.add(item);
        valorBruto += item.getValor();
        impostos += item.getValor() * 0.05;

        return this;
    }

    public NotaFiscalBuilder comObservacoes(String observacoes) {
        this.observacoes = observacoes;

        return this;
    }

    public NotaFiscalBuilder naData(Calendar data) {
        this.data = data;

        return this;
    }

    public NotaFiscal build() {
        return new NotaFiscal(razaoSocial, cnpj, data, valorBruto, impostos, todosItens, observacoes);
    }
}