package br.edu.cafeteria.excecao;

public class ClienteInexistenteException extends Exception {
    
    public ClienteInexistenteException() {
        super("Este cadastro não existe; tente novamente");
    }

    public ClienteInexistenteException(String mensagem) {
        super(mensagem);
    }
}