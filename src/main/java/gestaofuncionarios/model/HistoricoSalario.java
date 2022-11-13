package gestaofuncionarios.model;

import java.time.LocalDate;

public class HistoricoSalario {

    private int idHistoricoBonus;
    private int idFuncionario;
    private double valorTotalBonus;
    private double valorTotalSalario;
    private LocalDate dataInclusao;

    public HistoricoSalario(int idFuncionario, double valorTotalBonus, double valorTotalSalario, LocalDate dataInclusao) {
        this.idFuncionario = idFuncionario;
        this.valorTotalBonus = valorTotalBonus;
        this.valorTotalSalario = valorTotalSalario;
        this.dataInclusao = dataInclusao;
    }
      
    public int getIdHistoricoBonus() {
        return idHistoricoBonus;
    }

    public void setIdHistoricoBonus(int idHistoricoBonus) {
        this.idHistoricoBonus = idHistoricoBonus;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public double getValorTotalBonus() {
        return valorTotalBonus;
    }

    public void setValorTotalBonus(double valorTotalBonus) {
        this.valorTotalBonus = valorTotalBonus;
    }

    public double getValorTotalSalario() {
        return valorTotalSalario;
    }

    public void setValorTotalSalario(double valorTotalSalario) {
        this.valorTotalSalario = valorTotalSalario;
    }

    public LocalDate getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(LocalDate dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

}
