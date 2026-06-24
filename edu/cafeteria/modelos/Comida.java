package modelos;

import servico.IPromocional;

public class Comida extends Produto implements IPromocional {

    private int tempoPreparo;
    private boolean vegano;
    private boolean semGluten;

    public Comida(String codigo, String nome, double precoBase, int estoque, int tempoPreparo, boolean vegano, boolean semGluten){
        super(codigo, nome, precoBase, estoque);
        this.tempoPreparo = tempoPreparo;
        this.vegano = vegano;
        this.semGluten = semGluten;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public boolean isVegano() {
        return vegano;
    }

    public boolean isSemGluten() {
        return semGluten;
    }

    @Override
    public void aplicarPromocao() {

    }
}

