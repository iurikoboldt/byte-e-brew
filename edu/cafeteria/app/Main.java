package app;
import javax.swing.JOptionPane;

import modelos.ClienteStandard;
import modelos.Bebida;
import modelos.Cliente;
import modelos.Produto;


public class Main {
    public static void main(String[] args) {
        while (true) {                
            try {
                int opcao = Integer.parseInt(JOptionPane.showInputDialog("""
                Bem-vindo ao Byte & Brew!
                    
                Você tem cadastro? (1 - Sim; 2 - Não)
                """));
                
                if (opcao != 1 && opcao != 2) {
                    throw new IllegalArgumentException("Opção inválida; tente dnovamente.");
                }

                switch (opcao) {
                    case 1:
                        try {
                        String entrada1 = JOptionPane.showInputDialog("""
                        Digite seu cadastro (Nome e CPF):
                        """);
                        String[] valores1 = entrada1.split(" ");
                        String nome1 = valores1[0];
                        String cpf1 = valores1[1];
                        
                        if ()
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        /*botar na lista de clientes*/               
                        
                        //System.out.println(nome1+" "+cpf1);
                        break;                
                    case 2:
                        String entrada2 = JOptionPane.showInputDialog("""
                        Crie seu cadastro (Nome e CPF):
                        """);
                        String[] valores2 = entrada2.split(" ");
                        String nome2 = (valores2[0]);
                        String cpf2 = (valores2[1]);
                        Cliente cliente = new Cliente(nome2, cpf2); 
                            
                        ;

                        //criaçao do arquivo e botar na lista de clientes
                        break;
                    default:
                        break;
                }   
                break;                
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());      
                } 
        }
    }
}