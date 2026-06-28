package br.edu.cafeteria.modelo;

public class Bebida extends Produto{
    private TamanhoBebida tamanho;
    private double cafeinaMg;

    public Bebida(String codigo, String nome, double precoBase, int estoque, TamanhoBebida tamanho, double cafeinaMg){
        super(codigo, nome, precoBase, estoque);
        this.tamanho = tamanho;
        this.cafeinaMg = cafeinaMg;
    }

    public TamanhoBebida getTamanho() {
        return tamanho;}

    public double getCafeinaMg() {
        return cafeinaMg;}



    @Override
    public void aplicarPromocao() {

    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - R$ %.2f | Cafeína: %.0fmg (Estoque: %d)",
                getCodigo(), getNome(), tamanho.getDesc(), getPrecoBase(), cafeinaMg, getEstoque());
    }
}
