package modelos;


public class Cliente {
    
        boolean clienteVip;
        int experiencia;
        double dinheiroGasto;
        int cpf;
    
        public Cliente(int cpf, boolean clienteVip, double dinheiroGasto, int experiencia) {
            this.cpf = cpf;
            this.clienteVip = clienteVip;
            this.dinheiroGasto = dinheiroGasto;
            this.experiencia = experiencia;
        }
    
        public void conversaoDeExperiencia(double dinheiroGasto, int experiencia) {
            experiencia += dinheiroGasto;
        }        
        
        public double getExperiencia(){
            return experiencia;
        }


    }
