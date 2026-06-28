package br.edu.cafeteria.servico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.cafeteria.modelos.Produto;

public class Pedido {
    private static final String FILE = "data/codigo.txt";
    private static int ultimoCodigo = carregarCodigo();

    private final int codigo;
    private final List<Produto> listaDeItens = new ArrayList<>();

    public Pedido() {
        this.codigo = ++ultimoCodigo;
        salvarCodigo();
    }

    public void adicionarItem(Produto produto) {
        if (produto != null) {
            listaDeItens.add(produto);
        }
    }

    public double calcularSubtotal(Produto produto) {
        if (produto == null) {
            return 0.0;
        }
        return produto.getPrecoBase();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (produto == null || quantidade <= 0) {
            return;
        }

        for (int i = 0; i < quantidade; i++) {
            listaDeItens.add(produto);
        }
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (Produto produto : listaDeItens) {
            total += produto.getPrecoBase();
        }
        return total;
    }

    public void mostrarItens() {
        for (Produto produto : listaDeItens) {
            System.out.println(produto.getNome());
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Produto> getItens() {
        return listaDeItens;
    }

    public double getValorTotal() {
        return calcularValorTotal();
    }

    private static void salvarCodigo() {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(String.valueOf(ultimoCodigo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int carregarCodigo() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linha = br.readLine();
            if (linha != null && !linha.isBlank()) {
                return Integer.parseInt(linha);
            }
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
        return 0;
    }
}
