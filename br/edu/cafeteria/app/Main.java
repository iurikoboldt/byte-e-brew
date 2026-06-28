package br.edu.cafeteria.app;
import javax.swing.JOptionPane;

import br.edu.cafeteria.modelos.ClienteStandard;
import br.edu.cafeteria.excecao.ClienteInexistenteException;
import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.modelos.Bebida;
import br.edu.cafeteria.modelos.Cliente;
import br.edu.cafeteria.modelos.Produto;
import br.edu.cafeteria.servico.GerenciadorVendas;
import br.edu.cafeteria.servico.Pedido;


public class Main {
    public static void main(String[] args) throws CpfInvalidoException, ClienteInexistenteException {
        while (true) {                
            try {
                int opcao1 = Integer.parseInt(JOptionPane.showInputDialog("""
                Bem-vindo ao Byte & Brew!
                    
                Você tem cadastro? (1 - Sim; 2 - Não)
                """));
                // if (opcao != 1 && opcao != 2) {
                //     throw new IllegalArgumentException("Opção inválida; tente dnovamente.");
                // }
                switch (opcao1) {
                    case 1:
                        try {
                        String entrada = JOptionPane.showInputDialog("""
                        Digite seu cadastro (Nome e CPF):
                        """);
                        GerenciadorVendas.separarNomeECpf(entrada);
                        } catch (CpfInvalidoException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                        /*botar na lista de clientes*/               
                        break;                
                    case 2:
                        try {
                        String entrada = JOptionPane.showInputDialog("""
                        Crie seu cadastro (Nome e CPF):
                        """);
                        GerenciadorVendas.separarNomeECpf(entrada);
                        } catch (CpfInvalidoException e) {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }    
                    }//criaçao do arquivo e botar na lista de clientes
                        
                        break;
                    default:
                        throw new IllegalArgumentException("Opção Inválida, tente novamente.");
                }
                int opcao2 = Integer.parseInt(JOptionPane.showInputDialog("""
                O Que Gostaria de fazer agora?

                1 - Ver cardápio de poções 
                2 - Ver cardápio de comestíveis
                3 - Ver registro de clientes
                4 - 
                """));

                Pedido pedido = new Pedido();
                JOptionPane.showMessageDialog(null, "Comanda aberta com código " + pedido.getCodigo());

                String adicionarMais = JOptionPane.showInputDialog("Deseja adicionar um item à comanda? (s/n)");
                while ("s".equalsIgnoreCase(adicionarMais)) {
                    String nome = JOptionPane.showInputDialog("Nome do produto:");
                    double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do produto:"));

                    Produto produto = new Bebida(
                        "B" + pedido.getCodigo(),
                        nome,
                        preco,
                        10,
                        "M",
                        0
                    );
                    pedido.adicionarItem(produto);

                    adicionarMais = JOptionPane.showInputDialog("Adicionar outro item? (s/n)");
                }

                JOptionPane.showMessageDialog(
                    null,
                    "Comanda " + pedido.getCodigo() + "\nTotal: R$ " + String.format("%.2f", pedido.getValorTotal())
                );

                break;                
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());      
                
                } 
        }    
}
                