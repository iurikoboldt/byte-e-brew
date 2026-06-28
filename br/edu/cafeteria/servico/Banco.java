package br.edu.cafeteria.servico;

import java.util.List;

public interface Banco<T> {
    boolean adicionar(T item);
    boolean remover(T item);
    T buscar(T item);
    List<T> listar();
}
