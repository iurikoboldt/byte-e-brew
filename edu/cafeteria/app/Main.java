package app;
import javax.swing.JOptionPane;

import modelos.Cliente;
import modelos.Produto;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showInputDialog("fodase");
        System.out.println("""

            Bem-vindo ao Byte & Brew!

            O que você gostaria de fazer?
            
            1. Mostrar Cardápio
            2.

            """);
    
    Cliente josias = new Cliente(0, false, 10, 0);

    josias.conversaoDeExperiencia(10, 0);

    System.out.println(josias.getExperiencia());

    }
}