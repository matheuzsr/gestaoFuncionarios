package gestaofuncionarios.model;

import java.util.Date;

public class Bonus {

    private int idBonus;
    private final String tipo;
    private final double valor;
    private final Date data;

    public Bonus(String tipo, double valor, Date data) {
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }

    public int getIdBonus() {
        return idBonus;
    }

    public void setIdBonus(int id) {
        this.idBonus = id;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }
    
    public Date getData(){
        return data; 
    }


    @Override
    public String toString() {
        return "#"
                + this.tipo + "#"
                + this.getValor() + "#"
                + this.getData();
    }
}
