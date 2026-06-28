package br.edu.cafeteria.excecao;

public class PontosInsuficientesException extends Exception {

    public PontosInsuficientesException() {
        super("Pontos XP insuficientes para realizar o pagamento.");
    }

    public PontosInsuficientesException(String mensagem) {
        super(mensagem);
    }
}
