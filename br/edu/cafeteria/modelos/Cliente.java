package br.edu.cafeteria.modelos;

import java.io.Serializable;

public abstract class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    protected double xp;

    public Cliente(String nome, String cpf) {
        this(nome, cpf, 0.0);
    }

    protected Cliente(String nome, String cpf, double xp) {
        this.nome = nome;
        this.cpf = cpf;
        this.xp = xp;
    }
    
    public static Cliente definirFidelidade(String nome, String cpf){
        int numero = (int)(Math.random()*100);
        if (numero > 90) {
            return new ClienteVip(nome, cpf);
        } else {
            return new ClienteStandard(nome, cpf);
        }
    }

    public abstract int calcularXP(double valorCompra);

    public void adicionarXP(double valorCompra){
        this.xp += calcularXP(valorCompra);
    }

    public static boolean validarCPF(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }
        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                return false;
            }
        }
        return true;
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