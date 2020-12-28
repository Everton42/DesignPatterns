package decorator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Decorator {
    public static void main(String[] args) {

        Conta contaSaldoMaior500Mil = new Conta("Joaquin");
        contaSaldoMaior500Mil.deposita(8000000);

        Conta contaSaldoMenor500 = new Conta("Reginaldo");
        contaSaldoMenor500.deposita(70);

        List<Conta> contas = new ArrayList<Conta>();
        contas.add(contaSaldoMaior500Mil);
        contas.add(contaSaldoMenor500);

        Filtro filtro = new FiltroMenorQue100Reais(new FiltroMaiorQue500MilReais());
        List<Conta> contasFiltradas = filtro.filtra(contas);

        Filtro filtroMaiorQue500MilReais = new FiltroMaiorQue500MilReais();
        List<Conta> contasFiltradas2 = filtroMaiorQue500MilReais.filtra(contas);

        System.out.println("Filtro Maior Que 500 Mil Reais e Filtro Menor Que 100 Reais:");
        for (Conta conta : contasFiltradas) {
            System.out.println("Titular: " + conta.getTitular() + " Saldo: " + conta.getSaldo());
        }

        System.out.println("Filtro Maior Que 500 MilReais:");
        for (Conta conta : contasFiltradas2) {
            System.out.println("Titular: " + conta.getTitular() + " Saldo: " + conta.getSaldo());
        }
    }
}

abstract class Filtro {
    protected Filtro outroFiltro;

    public Filtro(Filtro outroFiltro) {
        this.outroFiltro = outroFiltro;
    }

    public Filtro() {
    }

    public abstract List<Conta> filtra(List<Conta> contas);

    protected List<Conta> proximo(List<Conta> contas) {
        if (outroFiltro != null)
            return outroFiltro.filtra(contas);
        else
            return new ArrayList<Conta>();
    }
}

class FiltroMenorQue100Reais extends Filtro {
    public FiltroMenorQue100Reais(Filtro outroFiltro) {
        super(outroFiltro);
    }

    public FiltroMenorQue100Reais() {
        super();
    }

    public List<Conta> filtra(List<Conta> contas) {
        List<Conta> filtrada = new ArrayList<Conta>();
        for (Conta conta : contas) {
            if (conta.getSaldo() < 100)
                filtrada.add(conta);
        }

        filtrada.addAll(proximo(contas));
        return filtrada;
    }
}

class FiltroMaiorQue500MilReais extends Filtro {
    public FiltroMaiorQue500MilReais(Filtro outroFiltro) {
        super(outroFiltro);
    }

    public FiltroMaiorQue500MilReais() {
        super();
    }

    public List<Conta> filtra(List<Conta> contas) {
        List<Conta> filtrada = new ArrayList<Conta>();
        for (Conta conta : contas) {
            if (conta.getSaldo() > 500000)
                filtrada.add(conta);
        }

        filtrada.addAll(proximo(contas));
        return filtrada;
    }
}

class FiltroMesmoMes extends Filtro {
    public FiltroMesmoMes(Filtro outroFiltro) {
        super(outroFiltro);
    }

    public FiltroMesmoMes() {
        super();
    }

    public List<Conta> filtra(List<Conta> contas) {
        List<Conta> filtrada = new ArrayList<Conta>();
        for (Conta conta : contas) {
            if (ehMesmoMes(conta))
                filtrada.add(conta);
        }

        filtrada.addAll(proximo(contas));
        return filtrada;
    }

    private boolean ehMesmoMes(Conta conta) {
        return conta.getDataAbertura().get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                && ((Conta) conta).getDataAbertura().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR);
    }
}