package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.Pedido;

public interface Promocional {
    
    double aplicarDescontoEventoGeek(Pedido pedido) throws IllegalArgumentException;
}


/* Implementação */

package br.edu.cafeteria.servico;

import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Pedido;

public class GerenciadorVendas implements Promocional {
    
    /**
     * Construtor padrão do GerenciadorVendas.
     */
    public GerenciadorVendas() {
    }
    
    /**
     * Valida se é possível adicionar um item ao pedido verificando o estoque.
     * 
     * <p><strong>Regra de Negócio:</strong> A quantidade solicitada não pode exceder
     * o estoque disponível do produto.</p>
     * 
     * @param item o ItemPedido contendo o produto e quantidade desejada
     * @throws EstoqueInsuficienteException se a quantidade solicitada exceder o estoque
     * @throws IllegalArgumentException se o item for nulo
     */
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
        
        // Conversão: 10 XP = R$ 1,00, logo para R$ X precisamos X * 10 XP
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
    
    /**
     * Aplica o desconto de "Dia de Evento Geek" ao pedido.
     */
    @Override
    public double aplicarDescontoEventoGeek(Pedido pedido) throws IllegalArgumentException {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        
        if (pedido.estaVazio()) {
            throw new IllegalArgumentException("Pedido não pode estar vazio para aplicar desconto");
        }
        
        // Calcula o total de bebidas no pedido usando Polimorfismo por Inclusão
        double totalBebidas = 0.0;
        
        // Itera sobre todos os itens do pedido
        for (ItemPedido item : pedido.getItens()) {
            // Verifica se o produto é uma instância de Bebida (Polimorfismo)
            if (item.getProduto() instanceof Bebida) {
                // Se for Bebida, adiciona seu subtotal ao total de bebidas
                totalBebidas += item.calcularSubtotal();
            }
            // Se não for Bebida (ex: Comida), simplesmente ignora
        }
        
        // Calcula 10% de desconto no total de bebidas
        double desconto = totalBebidas * 0.10;
        
        // Aplica o desconto ao pedido
        pedido.setDesconto(desconto);
        
        return desconto;
    }
    
    /**
     * Processa a adição de um item ao pedido com validação de estoque.
     */
    public void adicionarItemAoPedido(Pedido pedido, ItemPedido item) 
            throws EstoqueInsuficienteException {
        
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        
        // Valida o estoque antes de adicionar
        validarEstoque(item);
        
        // Se passou na validação, adiciona o item ao pedido
        pedido.adicionarItem(item);
    }
}
