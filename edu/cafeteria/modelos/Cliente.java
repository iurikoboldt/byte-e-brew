package modelos;
import java.util.ArrayList;
import servico.Banco;


public abstract class Cliente {
    private String nome;
    private String cpf;
    protected double xp;
    Banco<Cliente> clientes;

    public Cliente(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.xp = 0.0;
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