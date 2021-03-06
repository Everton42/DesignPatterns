package strategy;

import java.math.BigDecimal;

interface Imposto {
    BigDecimal calcular(BigDecimal valor);
}

class ISS implements Imposto {
    private static final BigDecimal PORCENTAGEM = BigDecimal.valueOf(0.06);

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(PORCENTAGEM);
    }
}

class ICMS implements Imposto {
    private static final BigDecimal PORCENTAGEM = BigDecimal.valueOf(0.05);
    private static final BigDecimal VALOR_FIXO = BigDecimal.valueOf(10);

    @Override
    public BigDecimal calcular(BigDecimal valor) {
        return valor.multiply(PORCENTAGEM).add(VALOR_FIXO);
    }

}

class ICCC implements Imposto {

    @Override
    public BigDecimal calcular(BigDecimal valor) {

        if (valor.doubleValue() < 1000) {
            return valor.multiply(BigDecimal.valueOf(0.05));
        }

        if (valor.doubleValue() >= 1000 || valor.doubleValue() <= 3000) {
            return valor.multiply(BigDecimal.valueOf(0.07));
        }

        return valor.multiply(BigDecimal.valueOf(0.08));
    }
}

class Orcamento {
    private BigDecimal valor;

    public Orcamento(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}

class CalculadorDeImpostos {
    public BigDecimal calcularImposto(Orcamento orcamento, Imposto imposto) {
        return imposto.calcular(orcamento.getValor());
    }
}

/**
 * Strategy
 */
public class Strategy {
    private static final Orcamento ORCAMENTO = new Orcamento(BigDecimal.valueOf(1000));

    public static void main(String[] args) {

        BigDecimal icmsCalculado = new CalculadorDeImpostos().calcularImposto(ORCAMENTO, new ICMS());
        BigDecimal issCalculado = new CalculadorDeImpostos().calcularImposto(ORCAMENTO, new ISS());
        BigDecimal icccCalculado = new CalculadorDeImpostos().calcularImposto(ORCAMENTO, new ICCC());

        System.out.println(icmsCalculado);
        System.out.println(issCalculado);
        System.out.println(icccCalculado);
    }
}