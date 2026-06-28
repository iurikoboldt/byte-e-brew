package br.edu.cafeteria.modelo;
public class Comida extends Produto {

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
    public String toString() {
        return String.format("[%s] %s - R$ %.2f | %d min | Vegano: %s | S/ Glúten: %s (Estoque: %d)",
                getCodigo(), getNome(), getPrecoBase(), tempoPreparo, vegano?"Sim":"Não", semGluten?"Sim":"Não", getEstoque());
    }
}

