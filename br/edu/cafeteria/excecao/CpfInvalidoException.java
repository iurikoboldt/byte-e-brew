package br.edu.cafeteria.excecao;

public class CpfInvalidoException extends Exception {

    public CpfInvalidoException() {
        super("CPF Inválido; tente novamente");
    }

    public CpfInvalidoException(String mensagem) {
        super(mensagem);
    }
}
