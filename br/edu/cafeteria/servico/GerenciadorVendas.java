package br.edu.cafeteria.servico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.edu.cafeteria.excecao.ClienteInexistenteException;
import br.edu.cafeteria.excecao.CpfInvalidoException;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelos.Bebida;
import br.edu.cafeteria.modelos.ClienteVip;
import br.edu.cafeteria.servico.Pedido;
import br.edu.cafeteria.modelos.Produto;


public class GerenciadorVendas implements IPromocional {
    public GerenciadorVendas() {
    }
 
    public void validarEstoque(Produto item) throws EstoqueInsuficienteException {
        if (item == null) {
            throw new IllegalArgumentException("Item do pedido não pode ser nulo");
        }
        
        if (item.getEstoque() < 0) {
            throw new EstoqueInsuficienteException(
                String.format(
                    "Estoque insuficiente para '%s': disponível %d unidade(s)",
                    item.getNome(),
                    item.getEstoque()
                )
            );
        }
    }
    
    public void validarSaldoXP(ClienteVip clienteVip, double totalPedido) 
            throws PontosInsuficientesException {
        
        if (clienteVip == null) {
            throw new IllegalArgumentException("Cliente VIP não pode ser nulo");
        }
        
        if (totalPedido < 0) {
            throw new IllegalArgumentException("Total do pedido não pode ser negativo");
        }
        
        double xpNecessario = totalPedido * 10.0;
        
        if (clienteVip.getXp() < xpNecessario) {
            throw new PontosInsuficientesException(
                String.format(
                    "Saldo de XP insuficiente para o cliente '%s': necessário %.2f XP (R$ %.2f), disponível %.2f XP (R$ %.2f)",
                    clienteVip.getNome(),
                    xpNecessario,
                    totalPedido,
                    clienteVip.getXp()
                )
            );
        }
    }

    @Override
    public double aplicarDescontoEventoGeek(Pedido pedido, List<Produto> lista) {
        int totalBebidas = 0;

        for (Produto item : pedido.getItens()) {
            if (item instanceof Bebida) {
                totalBebidas += item.getPrecoBase();
            }
        }

        LocalDate dia = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        String textoDia = dia.format(formatter);

        if ("14/03".equals(textoDia)) {
           return 0.1;
        } else {
           return 0.0;
        }
    }
    
    public static void separarNomeECpf(String entrada) throws CpfInvalidoException {
        String[] valores = entrada.split(" ");
        String nome = valores[0];
        String cpf = valores[1];

    }

    public void adicionarItemAoPedido(Pedido pedido, Produto item) 
            throws EstoqueInsuficienteException {
        
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        
        validarEstoque(item);
        
        pedido.adicionarItem(item);
    }

}
