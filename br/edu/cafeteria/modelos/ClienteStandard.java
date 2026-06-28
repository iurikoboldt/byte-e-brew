package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {

    public ClienteStandard(String nome, String cpf) {
        this(nome, cpf, 0.0);
    }

    public ClienteStandard(String nome, String cpf, double xp) {
        super(nome, cpf, xp);
    }
    @Override
    public int calcularXP(double valorCompra){
        if (valorCompra <= 0) return 0;
        return (int) Math.floor(valorCompra);
    }
}
