package gestaofuncionarios.dto;

import java.time.LocalDate;

public class HistoricoCalcularSalarioDTO {
    private String nomeFuncionario;
    private LocalDate dataCalculo;
    private Double valorBonus;
    private Double valorSalarioBase;
    private Double valorSalrio;

    public HistoricoCalcularSalarioDTO(String nomeFuncionario, LocalDate dataCalculo, Double valorBonus, Double valorSalarioBase, Double valorSalrio) {
        this.nomeFuncionario = nomeFuncionario;
        this.dataCalculo = dataCalculo;
        this.valorBonus = valorBonus;
        this.valorSalarioBase = valorSalarioBase;
        this.valorSalrio = valorSalrio;
    }
    

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDate dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public Double getValorBonus() {
        return valorBonus;
    }

    public void setValorBonus(Double valorBonus) {
        this.valorBonus = valorBonus;
    }

    public Double getValorSalarioBase() {
        return valorSalarioBase;
    }

    public void setValorSalarioBase(Double valorSalarioBase) {
        this.valorSalarioBase = valorSalarioBase;
    }

    public Double getValorSalrio() {
        return valorSalrio;
    }

    public void setValorSalrio(Double valorSalrio) {
        this.valorSalrio = valorSalrio;
    }





}
