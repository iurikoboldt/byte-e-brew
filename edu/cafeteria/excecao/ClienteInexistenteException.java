package excecao;

public class ClienteInexistenteException extends Exception {
    
    public ClienteInexistenteException() {
        super("CPF inválido; tente novamente");
    }

    public ClienteInexistenteException(String mensagem) {
        super(mensagem);
    }
}