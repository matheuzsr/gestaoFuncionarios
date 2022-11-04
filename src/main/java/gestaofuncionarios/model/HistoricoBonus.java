package gestaofuncionarios.model;

import java.time.LocalDate;

public class HistoricoBonus {

    private int idHistoricoBonus;
    private final int idFuncionario;
    private final int idBonus;
    private final LocalDate dataInclusao;

    public HistoricoBonus(int idFuncionario, int idBonus, LocalDate dataInclusao) {
        this.idFuncionario = idFuncionario;
        this.idBonus = idBonus;
        this.dataInclusao = dataInclusao;
    }

    public int getIdHistoricoBonus() {
        return idHistoricoBonus;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getIdBonus() {
        return idBonus;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }


    public void setIdHistoricoBonus(int idHistoricoBonus){
         this.idHistoricoBonus = idHistoricoBonus;
    }
 
}
