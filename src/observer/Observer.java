package observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    public static void main(String[] args) {
        List<AcoesEmissaoNF> acoesNf = new ArrayList<>();
        acoesNf.add(new EnviadorDeEmail());
        acoesNf.add(new Logger());
        NotaFiscal nf = new NotaFiscalBuilder(acoesNf).build();
    }
}
