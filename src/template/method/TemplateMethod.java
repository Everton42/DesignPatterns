package template.method;

import java.util.List;
import java.util.stream.Collectors;

import chainofresponsability.Item;
import chainofresponsability.Orcamento;

public class TemplateMethod {
    public static void main(String[] args) {
        Orcamento orcamento500 = new Orcamento(500);
        Orcamento orcamento600 = new Orcamento(600);
        orcamento600.adicionaItem(new Item("Mouse", 120));

        double valorICPP = new ICPP().calcula(orcamento500);
        double valorIKCV = new IKCV().calcula(orcamento500);
        double valorIKCV600 = new IKCV().calcula(orcamento600);

        System.out.println("valor ICPP $500: " + valorICPP);
        System.out.println("valor IKCV $500: " + valorIKCV);
        System.out.println("valor IKCV $600: " + valorIKCV600);
    }
}

interface Imposto {
    public double calcula(Orcamento orcamento);
}

abstract class TemplateDeImposto implements Imposto {

    @Override
    public double calcula(Orcamento orcamento) {
        if (deveUsarMaximaTaxacao(orcamento))
            return maximaTaxacao(orcamento);

        return minimaTaxacao(orcamento);
    }

    protected abstract boolean deveUsarMaximaTaxacao(Orcamento orcamento);

    protected abstract double maximaTaxacao(Orcamento orcamento);

    protected abstract double minimaTaxacao(Orcamento orcamento);
}

final class ICPP extends TemplateDeImposto {

    @Override
    protected boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() > 500;
    }

    @Override
    protected double maximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.07;
    }

    @Override
    protected double minimaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.05;
    }
}

final class IKCV extends TemplateDeImposto {

    @Override
    protected boolean deveUsarMaximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() > 500 && AlgumItemMaiorQueCem(orcamento.getItens());
    }

    @Override
    protected double maximaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.1;
    }

    @Override
    protected double minimaTaxacao(Orcamento orcamento) {
        return orcamento.getValor() * 0.06;
    }

    private boolean AlgumItemMaiorQueCem(List<Item> itens) {
        return itens.stream().filter(its -> its.getValor() > 100).collect(Collectors.toList()) != null;
    }
}