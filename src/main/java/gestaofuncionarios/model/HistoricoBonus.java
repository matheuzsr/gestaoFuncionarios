package gestaofuncionarios.model;

import java.time.LocalDate;

public class HistoricoBonus {

    private int idHistoricoBonus;
    private final int idFuncionario;
    private final int idBonus;
    private double valorBonusRecebido;
    private final LocalDate dataInclusao;

    public HistoricoBonus(int idFuncionario, int idBonus, LocalDate dataInclusao, double valorBonusRecebido) {
        this.idFuncionario = idFuncionario;
        this.idBonus = idBonus;
        this.dataInclusao = dataInclusao;
        this.valorBonusRecebido = valorBonusRecebido;
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

    public double getValorBonusRecebido() {
        return valorBonusRecebido;
    }

    public void setValorBonusRecebido(double valorBonusRecebido) {
        this.valorBonusRecebido = valorBonusRecebido;
    }
}
