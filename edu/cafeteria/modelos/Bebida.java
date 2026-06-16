package modelos;

public class Bebida extends Produto {

    private String tamanho;
    private String temperaturaDaBebida;
    private double doseDeCafeina;

    public Bebida(String nome, int preco, String tamanho, String temperaturaDaBebida, double doseDeCafeina) {
        super(nome, preco);
        this.tamanho = tamanho;
        this.temperaturaDaBebida = temperaturaDaBebida;
        this.doseDeCafeina = doseDeCafeina;
    }

    

}
