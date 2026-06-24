package app;
import javax.swing.JOptionPane;

import modelos.ClienteStandard;
import modelos.Cliente;


public class Main {
    public static void main(String[] args) {
        while (true) {                
            try {
                String opcaoString = JOptionPane.showInputDialog("""
                Bem-vindo ao Byte & Brew!
                    
                Você tem cadastro? (1 - Sim; 2 - Não)
                """);
                
                switch (opcaoString) {
                    case "1":
                        String entrada1 = JOptionPane.showInputDialog("""
                        Digite seu cadastro (Nome e CPF):
                        """);
                        String[] valores1 = entrada1.split(" ");
                        String nome1= (valores1[0]);
                        String cpf1 = (valores1[1]);
                        
                        

                        System.out.println(nome1+" "+cpf1);
                        
                        break;
                
                        //criaçao do arquivo e botar na lista de clientes
                        //break;
                    
                    case "2":
                        String entrada2 = JOptionPane.showInputDialog("""
                        Crie seu cadastro (Nome e CPF):
                        """);
                        String[] valores2 = entrada2.split(" ");
                        String nome2 = (valores2[0]);
                        String cpf2 = (valores2[1]);
                        
                        //criaçao do arquivo e botar na lista de clientes
                        break;
                    default:
                        break;
                }   
                break;
                
                // //josias.adicionarXP(10);

                // System.out.println(josias.getXp());
                } catch (Exception e) {
            }
        }
    }
}