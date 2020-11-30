package chainofresponsability;

public class Chain {
    public static void main(String[] args) {
        CalculadorDeDescontos descontos = new CalculadorDeDescontos();

        Orcamento orcamento500 = new Orcamento(500);
        orcamento500.adicionaItem(new Item("Mesa", 450.0));
        orcamento500.adicionaItem(new Item("Mouse pad", 50.0));

        Orcamento orcamento600 = new Orcamento(600);
        orcamento500.adicionaItem(new Item("Mesa", 500.0));
        orcamento500.adicionaItem(new Item("Mouse", 100.0));

        double descontoFinal500 = descontos.calcula(orcamento500);
        System.out.println("Desconto p/ 500: " + descontoFinal500);

        double descontoFinal600 = descontos.calcula(orcamento600);
        System.out.println("Desconto p/ 600: " + descontoFinal600);
    }
}

interface Desconto {
    double desconta(Orcamento orcamento);

    void setProximo(Desconto desconto);
}

class DescontoPorMaisDeCincoItens implements Desconto {
    private static final double DESCONTO = 0.1;
    private Desconto proximoDesconto;

    public double desconta(Orcamento orcamento) {
        if (quantidadeItensMaiorCinco(orcamento))
            return orcamento.getValor() * DESCONTO;
        return proximoDesconto.desconta(orcamento);
    }

    private boolean quantidadeItensMaiorCinco(Orcamento orcamento) {
        return orcamento.getItens().size() > 5;
    }

    @Override
    public void setProximo(Desconto desconto) {
        this.proximoDesconto = desconto;
    }
}

class DescontoPorMaisDeQuinhentosReais implements Desconto {
    private static final double DESCONTO = 0.07;
    private Desconto proximoDesconto;

    public double desconta(Orcamento orcamento) {
        if (orcamentoMaiorQuinhentos(orcamento)) {
            return orcamento.getValor() * DESCONTO;
        }
        return proximoDesconto.desconta(orcamento);
    }

    private boolean orcamentoMaiorQuinhentos(Orcamento orcamento) {
        return orcamento.getValor() > 500;
    }

    @Override
    public void setProximo(Desconto desconto) {
        this.proximoDesconto = desconto;

    }
}

class SemDesconto implements Desconto {

    public double desconta(Orcamento orcamento) {
        return 0;
    }

    public void setProximo(Desconto desconto) {
        return;
    }
}

class CalculadorDeDescontos {
    public double calcula(Orcamento orcamento) {
        Desconto d1 = new DescontoPorMaisDeCincoItens();
        Desconto d2 = new DescontoPorMaisDeQuinhentosReais();
        Desconto d3 = new SemDesconto();

        d1.setProximo(d2);
        d2.setProximo(d3);

        return d1.desconta(orcamento);
    }
}