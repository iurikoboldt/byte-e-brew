package br.edu.cafeteria.servico; // ou br.edu.cafeteria.servico, dependendo de onde o guardou

import java.util.List;
import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.servico.Pedido;

public interface Promocional {
    // Verifique se o método recebe o Pedido e a Lista de Produtos:
    double aplicarDescontoEventoGeek(Pedido pedido, List<Produto> produtos);
}
