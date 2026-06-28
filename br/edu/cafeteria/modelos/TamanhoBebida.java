package br.edu.cafeteria.modelo;

public enum TamanhoBebida {
    P("Pequeno"), M("Médio"), G("Grande");
    private final String desc;
    TamanhoBebida(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return desc;
    }
}


