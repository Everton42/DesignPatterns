package adapter;

public interface MaquinaCafe {
    void primeiraEscolha();

    void segundaEscolha();
}

class MaquinaCafeAntiga {
    public void opcaoA() {
        System.out.println("A - Selecionado");
    }

    public void opcaoB() {
        System.out.println("B - Selecionado");
    }
}

class MaquinaCafeTouchscreenAdapter implements MaquinaCafe {
    private MaquinaCafeAntiga maquinaCafeAntiga;

    public MaquinaCafeTouchscreenAdapter(MaquinaCafeAntiga maquinaCafeAntiga) {
        this.maquinaCafeAntiga = maquinaCafeAntiga;
    }

    @Override
    public void primeiraEscolha() {
        maquinaCafeAntiga.opcaoA();
    }

    @Override
    public void segundaEscolha() {
        maquinaCafeAntiga.opcaoB();
    }

}