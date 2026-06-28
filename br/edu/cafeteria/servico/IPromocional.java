package br.edu.cafeteria.servico;

import java.util.List;

import br.edu.cafeteria.modelos.Produto;


public interface IPromocional {
    public double aplicarDescontoEventoGeek(Pedido pedido, List<Produto> lista);
}