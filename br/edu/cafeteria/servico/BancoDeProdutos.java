package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelos.Produto;
import java.util.ArrayList;
import java.util.List;

public class BancoDeProdutos implements Banco<Produto> {
    private final ArrayList<Produto> produtos = new ArrayList<>();

    @Override
    public boolean adicionar(Produto produto) {
        return produtos.add(produto);
    }

    @Override
    public boolean remover(Produto produto) {
        return produtos.remove(produto);
    }

    @Override
    public Produto buscar(Produto produto) {
        int index = produtos.indexOf(produto);
        return index >= 0 ? produtos.get(index) : null;
    }

    public Produto buscarPorCodigo(String codigo) {
        for (Produto produto : produtos) {
            if (produto != null && produto.getCodigo().equals(codigo)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }
}
