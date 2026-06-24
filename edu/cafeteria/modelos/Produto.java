package modelos;
public abstract class Produto {

    private String codigo;
    private String nome;
    private double precoBase;
    private int estoque;

    public Produto(String codigo, String nome, double precoBase, int estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoBase = precoBase;
        this.estoque = estoque;
    }

    public boolean temEstoque(int quantidade){
        return estoque >= quantidade;
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade <= estoque) {
            estoque -= quantidade;
        }
    }
    public void aumentarEstoque(int quantidade){
        estoque += quantidade;
    }
    public double getPrecoBase(){
        return precoBase;
    }

    public String getNome() {
        return nome;
    }
    public int getEstoque(){

        return estoque;
    }
    public String getCodigo (){
        return codigo;
    }
}


