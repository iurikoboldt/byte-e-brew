package br.edu.cafeteria.app;

import javax.swing.JOptionPane;
import java.util.List;


import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteStandard;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.TamanhoBebida;


import br.edu.cafeteria.servico.BancoDeProdutos;
import br.edu.cafeteria.servico.BancoDeClientes;
import br.edu.cafeteria.servico.GerenciadorVendas;
import br.edu.cafeteria.servico.Pedido;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class MainApp {

    private static final BancoDeProdutos bancoProdutos = new BancoDeProdutos();
    private static final BancoDeClientes bancoClientes = new BancoDeClientes();
    private static final GerenciadorVendas gerenciadorVendas = new GerenciadorVendas();

    public static void main(String[] args) {
        povoarCardapioOficial();

        while (true) {
            String[] opcoesOpf = {"Fazer pedido", "Cadastrar-se", "Sair"};
            int menuPrincipal = JOptionPane.showOptionDialog(null,
                    "=== SEJA BEM-VINDO À BYTE && BREW ===\nEscolha o seu serviço:",
                    "Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesOpf, opcoesOpf[0]);

            if (menuPrincipal == 0) {
                executarFluxoVenda();
            } else if (menuPrincipal == 1) {
                gerenciarModuloClientes();
            } else {
                JOptionPane.showMessageDialog(null, "Que a força esteja com você! Obrigada pela preferência.");
                break;
            }
        }
    }

    private static void povoarCardapioOficial() {
        if (!bancoProdutos.listar().isEmpty()) return;


        bancoProdutos.adicionar(new Comida("LB01", "Lembas Bread", 10.50, 15, 10, false, true));
        bancoProdutos.adicionar(new Comida("PC02", "Portal Cake", 9.00, 15, 15, false, false));
        bancoProdutos.adicionar(new Comida("ARBR", "Array de Brigadeiro", 8.50, 15, 10, false, true));
        bancoProdutos.adicionar(new Comida("P314", "Lemon Pi", 12.00, 15, 20, false, false));


        bancoProdutos.adicionar(new Comida("SSD1", "Sanduíche Compilado", 8.50, 15, 8, false, false));
        bancoProdutos.adicionar(new Comida("AG15", "Anéis do Gollum", 15.00, 15, 20, true, false));
        bancoProdutos.adicionar(new Comida("CHFR", "Firewall de queijo", 6.00, 15, 10, false, false));
        bancoProdutos.adicionar(new Comida("OVE5", "Overlow de recheio", 9.50, 15, 15, true, false));


        bancoProdutos.adicionar(new Bebida("COFP", "Café do Programador", 6.00, 15, TamanhoBebida.P, 80.0));
        bancoProdutos.adicionar(new Bebida("JAV4", "Java coffee", 5.00, 15, TamanhoBebida.M, 150.0));
        bancoProdutos.adicionar(new Bebida("C4PC", "C++puccino", 8.50, 15, TamanhoBebida.P, 60.0));
        bancoProdutos.adicionar(new Bebida("PM01", "Poção de Mana", 5.80, 15, TamanhoBebida.M, 0.0));
        bancoProdutos.adicionar(new Bebida("BM02", "Blue milk", 10.00, 15, TamanhoBebida.M, 0.0));
        bancoProdutos.adicionar(new Bebida("BBUG", "Bobba Bugs", 12.00, 15, TamanhoBebida.G, 0.0));
        bancoProdutos.adicionar(new Bebida("BYCL", "Byte cold Latte", 10.00, 15, TamanhoBebida.G, 60.0));
    }

    private static void gerenciarModuloClientes() {
        String[] subOpcoes = {"Cadastrar", "Buscar", "Excluir", "Listar Todos", "Voltar"};
        int escolha = JOptionPane.showOptionDialog(null, "Painel de Clientes:", "CRUD Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, subOpcoes, subOpcoes[0]);

        if (escolha == 0) {
            String nome = JOptionPane.showInputDialog("Nome do cliente:");
            if (nome == null || nome.isEmpty()) return;
            String cpf = JOptionPane.showInputDialog("CPF (Apenas 11 dígitos):");
            if (cpf == null || !Cliente.validarCPF(cpf)) {
                JOptionPane.showMessageDialog(null, "CPF inválido de acordo com as regras de validação!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente novo = Cliente.definirFidelidade(nome, cpf);
            if (bancoClientes.adicionar(novo)) {
                JOptionPane.showMessageDialog(null, "Inscrito com sucesso como " + novo.getClass().getSimpleName() + "!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: CPF já cadastrado no arquivo JSON.");
            }
        } else if (escolha == 1) {
            String cpf = JOptionPane.showInputDialog("Insira o CPF para busca:");
            Cliente achado = bancoClientes.buscarPorCpf(cpf);
            if (achado != null) {
                JOptionPane.showMessageDialog(null, String.format("Cliente Encontrado:\nNome: %s\nTipo: %s\nSaldo XP: %.2f",
                        achado.getNome(), achado.getClass().getSimpleName(), achado.getXp()));
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não consta nos registros.");
            }
        } else if (escolha == 2) {
            String cpf = JOptionPane.showInputDialog("Insira o CPF do cadastro a ser REMOVIDO:");
            Cliente alvo = bancoClientes.buscarPorCpf(cpf);
            if (alvo != null && bancoClientes.remover(alvo)) {
                JOptionPane.showMessageDialog(null, "Inscrição removida da guilda.");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao remover ou cliente inexistente.");
            }
        } else if (escolha == 3) {
            StringBuilder sb = new StringBuilder("=== CLIENTES REGISTRADOS ===\n\n");
            List<Cliente> todos = bancoClientes.listar();
            if (todos.isEmpty()) sb.append("Nenhum registro encontrado.");
            for (Cliente c : todos) {
                sb.append(String.format("• %s | CPF: %s | [%s] | XP: %.1f\n", c.getNome(), c.getCpf(), c.getClass().getSimpleName(), c.getXp()));
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void executarFluxoVenda() {
        String cpf = JOptionPane.showInputDialog("CPF do Cliente para a computação de XP (Deixe vazio para Casual):");
        Cliente clienteAtual = null;

        if (cpf != null && !cpf.trim().isEmpty()) {
            clienteAtual = bancoClientes.buscarPorCpf(cpf.trim());
            if (clienteAtual == null) {
                JOptionPane.showMessageDialog(null, "Não identificado. Operação continuará como Cliente Casual.");
            } else {
                JOptionPane.showMessageDialog(null, "Bem-vindo de volta, " + clienteAtual.getNome() + "!");
            }
        }

        Pedido novoPedido = new Pedido();

        while (true) {
            StringBuilder stringCardapio = new StringBuilder("=== CARDÁPIO BYTE & BREW ===\nSelecione pelo CÓDIGO:\n\n");
            for (Produto prod : bancoProdutos.listar()) {
                stringCardapio.append(prod.toString()).append("\n");
            }
            stringCardapio.append("\nDigite 'FIM' para processar o fechamento.");

            String codInput = JOptionPane.showInputDialog(stringCardapio.toString());
            if (codInput == null || codInput.equalsIgnoreCase("FIM")) break;

            Produto selecionado = bancoProdutos.buscarPorCodigo(codInput.toUpperCase());
            if (selecionado == null) {
                JOptionPane.showMessageDialog(null, "Código inválido!");
                continue;
            }

            String qtdStr = JOptionPane.showInputDialog("Quantidade de " + selecionado.getNome() + ":");
            if (qtdStr == null) continue;

            try {
                int qtd = Integer.parseInt(qtdStr);
                if (qtd <= 0) throw new NumberFormatException();


                gerenciadorVendas.validarEstoque(selecionado, qtd);


                novoPedido.adicionarItem(selecionado, qtd);
                selecionado.reduzirEstoque(qtd);

                JOptionPane.showMessageDialog(null, qtd + "x " + selecionado.getNome() + " adicionado ao Pedido #" + novoPedido.getCodigo());

            } catch (EstoqueInsuficienteException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Alerta de Estoque", JOptionPane.WARNING_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantidade inválida!");
            }
        }

        if (novoPedido.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum item processado. Comanda descartada.");
            return;
        }

        double totalOriginal = novoPedido.getValorTotal();


        double taxaDesconto = gerenciadorVendas.aplicarDescontoEventoGeek(novoPedido, bancoProdutos.listar());


        if (taxaDesconto == 0.0) {
            int resp = JOptionPane.showConfirmDialog(null, "Deseja participar do Dia do Evento Geek (10% de desconto nas bebidas)?", "Simulador de Evento", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) taxaDesconto = 0.10;
        }

        double valorDesconto = 0.0;
        if (taxaDesconto > 0) {
            for (Produto p : novoPedido.getItens()) {
                if (p instanceof Bebida) {
                    valorDesconto += (p.getPrecoBase() * taxaDesconto);
                }
            }
        }
        double totalFinal = totalOriginal - valorDesconto;


        if (clienteAtual instanceof ClienteVIP) {
            ClienteVIP vip = (ClienteVIP) clienteAtual;
            int opXp = JOptionPane.showConfirmDialog(null,
                    String.format("Cliente VIP!\nSaldo Atual: %.1f XP\nCusto em Pontos: %d XP\nDeseja pagar usando seu saldo XP?",
                            vip.getXp(), (int)(totalFinal * ClienteVIP.XP_POR_REAL)), "Resgate de Pontos", JOptionPane.YES_NO_OPTION);

            if (opXp == JOptionPane.YES_OPTION) {
                try {

                    gerenciadorVendas.validarSaldoXP(vip, totalFinal);

                    int pontosNecessarios = (int) (totalFinal * ClienteVIP.XP_POR_REAL);
                    vip.removerXP(pontosNecessarios);


                    bancoClientes.atualizarXP(vip.getCpf(), 0);

                    JOptionPane.showMessageDialog(null, "Pedido liquidado com sucesso usando seus pontos XP!");
                    return;
                } catch (PontosInsuficientesException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage() + "\nRedirecionando para pagamento tradicional.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        JOptionPane.showMessageDialog(null, String.format("Pedido #%d Emitido!\nSubtotal: R$ %.2f\nDescontos: R$ %.2f\nTotal: R$ %.2f",
                novoPedido.getCodigo(), totalOriginal, valorDesconto, totalFinal));

        if (clienteAtual != null) {

            bancoClientes.atualizarXP(clienteAtual.getCpf(), totalFinal);
            JOptionPane.showMessageDialog(null, "Pontos de XP adicionados à conta do cliente!");
        }
    }
}
