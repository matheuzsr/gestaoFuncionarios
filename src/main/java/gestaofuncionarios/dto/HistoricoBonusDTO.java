package gestaofuncionarios.dto;

import java.time.LocalDate;

public class HistoricoBonusDTO {
    private String tipoBonus;
    private Double valorBonus;
    private LocalDate dataCalculo;
    private String cargoFuncionario;

    public HistoricoBonusDTO(String tipoBonus, Double valorBonus, LocalDate dataCalculo, String cargoFuncionario) {
        this.tipoBonus = tipoBonus;
        this.valorBonus = valorBonus;
        this.dataCalculo = dataCalculo;
        this.cargoFuncionario = cargoFuncionario;
    }

    public String getTipoBonus() {
        return tipoBonus;
    }

    public void setTipoBonus(String tipoBonus) {
        this.tipoBonus = tipoBonus;
    }

    public Double getValorBonus() {
        return valorBonus;
    }

    public void setValorBonus(Double valorBonus) {
        this.valorBonus = valorBonus;
    }

    public LocalDate getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDate dataCalculo) {
        this.dataCalculo = dataCalculo;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }


}
