package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.Pedido;

public interface Promocional {
    
    double aplicarDescontoEventoGeek(Pedido pedido) throws IllegalArgumentException;
}



package br.edu.cafeteria.servico;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Pedido;

public class GerenciadorVendas implements Promocional {
    

    public GerenciadorVendas() {
    }
    

    public void validarEstoque(ItemPedido item) throws EstoqueInsuficienteException {
        if (item == null) {
            throw new IllegalArgumentException("Item do pedido não pode ser nulo");
        }
        
        if (item.getQuantidade() > item.getProduto().getEstoque()) {
            throw new EstoqueInsuficienteException(
                String.format(
                    "Estoque insuficiente para '%s': solicitado %d unidade(s), " +
                    "disponível %d unidade(s)",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getEstoque()
                )
            );
        }
    }
    
    public void validarSaldoXP(ClienteVIP clienteVIP, double totalPedido) 
            throws PontosInsuficientesException {
        
        if (clienteVIP == null) {
            throw new IllegalArgumentException("Cliente VIP não pode ser nulo");
        }
        
        if (totalPedido < 0) {
            throw new IllegalArgumentException("Total do pedido não pode ser negativo");
        }
        

        double xpNecessario = totalPedido * 10.0;
        
        if (clienteVIP.getSaldoXP() < xpNecessario) {
            throw new PontosInsuficientesException(
                String.format(
                    "Saldo de XP insuficiente para o cliente '%s': " +
                    "necessário %.2f XP (R$ %.2f), disponível %.2f XP (R$ %.2f)",
                    clienteVIP.getNome(),
                    xpNecessario,
                    totalPedido,
                    clienteVIP.getSaldoXP(),
                    clienteVIP.getSaldoEmReais()
                )
            );
        }
    }
    
    
    @Override
    public double aplicarDescontoEventoGeek(Pedido pedido) throws IllegalArgumentException {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        
        if (pedido.estaVazio()) {
            throw new IllegalArgumentException("Pedido não pode estar vazio para aplicar desconto");
        }
        
     
        double totalBebidas = 0.0;
        
    
        for (ItemPedido item : pedido.getItens()) {
            
            if (item.getProduto() instanceof Bebida) {
               
                totalBebidas += item.calcularSubtotal();
            }
            
        }
        
        double desconto = totalBebidas * 0.10;
        
     
        pedido.setDesconto(desconto);
        
        return desconto;
    }
    
    public void adicionarItemAoPedido(Pedido pedido, ItemPedido item) 
            throws EstoqueInsuficienteException {
        
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        
  
        validarEstoque(item);
        
 
        pedido.adicionarItem(item);
    }
}
