package br.edu.cafeteria.excecao;

public class EstoqueInsuficienteException extends Exception {

    public EstoqueInsuficienteException() {
        super("Estoque insuficiente para realizar a operação.");
    }

    public EstoqueInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
