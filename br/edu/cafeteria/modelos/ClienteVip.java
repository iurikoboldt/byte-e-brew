package br.edu.cafeteria.modelos;

public class ClienteVip extends Cliente{
    public static final int XP_POR_REAL = 10 ;

    public ClienteVip(String nome, String cpf) {
        this(nome, cpf, 0.0);
    }

    public ClienteVip(String nome, String cpf, double xp) {
        super(nome, cpf, xp);
    }
    @Override
    public int calcularXP(double valorCompra){
        return (int) valorCompra * 2;
    }

    public boolean XPsuficiente(double valorCompra){
        int xpNecessario = (int) (valorCompra * XP_POR_REAL);
        return getXp() >= xpNecessario;
    }
}
