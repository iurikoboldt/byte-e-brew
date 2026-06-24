package modelos;

import servico.IPromocional;

public class Bebida extends Produto implements IPromocional{

    private String tamanho;
    private double cafeinaMg;

    public Bebida(String codigo, String nome, double precoBase, int estoque, String tamanho, double cafeinaMg){
        super(codigo, nome, precoBase, estoque);
        this.tamanho = tamanho;
        this.cafeinaMg = cafeinaMg;

    }
    public String getTamanho() {
        return tamanho;
    }

    public double getCafeinaMg() {
        return cafeinaMg;
    }

    @Override
    public void aplicarPromocao() {

    }
}
