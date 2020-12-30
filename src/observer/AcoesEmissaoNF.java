package observer;

public interface AcoesEmissaoNF {
    void executa(NotaFiscal notaFiscal);
}

class EnviadorDeEmail implements AcoesEmissaoNF {

    @Override
    public void executa(NotaFiscal notaFiscal) {
        System.out.println("Email enviado!");
    }

}

class Logger implements AcoesEmissaoNF {

    @Override
    public void executa(NotaFiscal notaFiscal) {
        System.out.println("log adicionado");
    }

}