package modelos;

public class ClienteVip extends Cliente{
    public static final int XP_POR_REAL = 10 ;

    public ClienteVip(String nome, String cpf){
        super(nome, cpf);
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
