package gestaofuncionarios.model;

public class Bonus {

    private int idBonus;
    private String tipo;

    public Bonus(String tipo) {
    	tipo = tipo;
    }
    
    public int getIdBonus() {
        return idBonus;
    }

    public void setIdBonus(int idBonus) {
        this.idBonus = idBonus;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
}
