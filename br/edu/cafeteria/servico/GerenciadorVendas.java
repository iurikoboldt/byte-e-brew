package br.edu.cafeteria.servico;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import br.edu.cafeteria.excecao.*;
import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.servico.Promocional;

public class GerenciadorVendas implements Promocional {

    public void validarEstoque(Produto item, int quantidadeSolicitada) throws EstoqueInsuficienteException {
        if (item == null) throw new IllegalArgumentException("Item nulo");
        if (!item.temEstoque(quantidadeSolicitada)) {
            throw new EstoqueInsuficienteException(
                    String.format("Estoque insuficiente para '%s': solicitado %d, disponível %d",
                            item.getNome(), quantidadeSolicitada, item.getEstoque())
            );
        }
    }

    public void validarSaldoXP(ClienteVIP clienteVip, double totalPedido) throws PontosInsuficientesException {
        if (clienteVip == null) throw new IllegalArgumentException("Cliente VIP nulo");
        double xpNecessario = totalPedido * ClienteVIP.XP_POR_REAL;
        if (clienteVip.getXp() < xpNecessario) {
            throw new PontosInsuficientesException(
                    String.format("Saldo XP insuficiente para '%s': necessário %.2f, disponível %.2f",
                            clienteVip.getNome(), xpNecessario, clienteVip.getXp())
            );
        }
    }

    @Override
    public double aplicarDescontoEventoGeek(Pedido pedido, List<Produto> lista) {
        // Retorna a taxa multiplicadora do desconto (0.10 se for o dia do evento)
        LocalDate dia = LocalDate.now();
        String textoDia = dia.format(DateTimeFormatter.ofPattern("dd/MM"));

        // Se preferir testar em tempo real sem travar na data 14/03, altere temporariamente para o dia atual!
        if ("14/03".equals(textoDia)) {
            return 0.10;
        }
        return 0.0;
    }
}
