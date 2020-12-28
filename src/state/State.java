package state;

public class State {
    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento();
        orcamento.valor = 500;
        System.out.println("Valor: " + orcamento.valor);
        System.out.println("Em aprovação");
        orcamento.aplicaDescontoExtra();
        System.out.println("Desconto aplicado: " + orcamento.valor);
        System.out.println("Aprovando");
        orcamento.aprova();
        orcamento.aplicaDescontoExtra();
        System.out.println("Desconto aplicado: " + orcamento.valor);
        System.out.println("Finalizando");
        orcamento.finaliza();
    }
}

class Orcamento {
    protected double valor;
    protected EstadoDeUmOrcamento estadoAtual;

    public Orcamento() {
        this.estadoAtual = new EmAprovacao();
    }

    public void aplicaDescontoExtra() {
        estadoAtual.aplicaDescontoExtra(this);
    }

    public void aprova() {
        estadoAtual.aprova(this);
    }

    public void reprova() {
        estadoAtual.reprova(this);
    }

    public void finaliza() {
        estadoAtual.finaliza(this);
    }

}

interface EstadoDeUmOrcamento {
    void aplicaDescontoExtra(Orcamento orcamento);

    void aprova(Orcamento orcamento);

    void reprova(Orcamento orcamento);

    void finaliza(Orcamento orcamento);
}

class EmAprovacao implements EstadoDeUmOrcamento {
    private boolean descontoAplicado = false;

    public void aplicaDescontoExtra(Orcamento orcamento) {
        if (descontoAplicado)
            throw new RuntimeException("O Desconto Extra já foi lançado!");

        orcamento.valor -= orcamento.valor * 0.05;
    }

    public void aprova(Orcamento orcamento) {
        orcamento.estadoAtual = new Aprovado();
    }

    public void reprova(Orcamento orcamento) {
        orcamento.estadoAtual = new Reprovado();
    }

    public void finaliza(Orcamento orcamento) {
        throw new RuntimeException("Orcamento em aprovação não podem ir para finalizado diretamente");
    }
}

class Aprovado implements EstadoDeUmOrcamento {
    private boolean descontoAplicado = false;

    public void aplicaDescontoExtra(Orcamento orcamento) {
        if (descontoAplicado)
            throw new RuntimeException("O Desconto Extra já foi lançado!");

        orcamento.valor -= orcamento.valor * 0.02;
    }

    public void aprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamento já está em estado de aprovação");
    }

    public void reprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamento está em estado de aprovação e não pode ser reprovado");
    }

    public void finaliza(Orcamento orcamento) {
        orcamento.estadoAtual = new Finalizado();
    }
}

class Reprovado implements EstadoDeUmOrcamento {
    public void aplicaDescontoExtra(Orcamento orcamento) {
    }

    public void aprova(Orcamento orcamento) {
        throw new RuntimeException("Orçamento está em estado de reprovação, não pode aprovar agora!");
    }

    public void reprova(Orcamento orcamento) {
        throw new RuntimeException("Já estou reprovado!");
    }

    public void finaliza(Orcamento orcamento) {
        orcamento.estadoAtual = new Finalizado();
    }
}

class Finalizado implements EstadoDeUmOrcamento {
    public void aplicaDescontoExtra(Orcamento orcamento) {
    }

    public void aprova(Orcamento orcamento) {
        throw new RuntimeException("Não posso mudar mais, já estou finalizado!");
    }

    public void reprova(Orcamento orcamento) {
        throw new RuntimeException("Não posso mudar mais, já estou finalizado!");
    }

    public void finaliza(Orcamento orcamento) {
        throw new RuntimeException("Não posso mudar mais, já estou finalizado!");
    }
}