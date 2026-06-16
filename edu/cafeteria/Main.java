import modelos.Cliente;
import modelos.Produto;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
            Bem-vindo ao Byte & Brew!

            O que você gostaria de fazer?
                
            """);
    
    Cliente josias = new Cliente(0, false, 10, 0);

    josias.conversaoDeExperiencia(10, 0);

    System.out.println(josias.getExperiencia());

    }
}