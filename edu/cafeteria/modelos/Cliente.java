package br.edu.cafeteria.modelo;

public abstract class Cliente {
    private String nome;
    private String cpf;
    private double xp;

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.xp = 0.0;
    }

    public abstract int calcularXP(double valorCompra);

    public void adicionarXP(double valorCompra){
        this.xp += calcularXP(valorCompra);
    }

    public String getCpf() {
        return cpf;
    }

    public double getXp() {
        return xp;
    }

    public String getNome(){
        return nome;
    }

    protected void removerXP(int quantidade){
        xp -= quantidade;
    }
}
