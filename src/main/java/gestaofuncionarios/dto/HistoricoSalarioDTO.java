package gestaofuncionarios.dto;

import java.time.LocalDate;

public class HistoricoSalarioDTO {
  double salarioCalculado;
  double bonusTotal;
  double valorEstatistica;
  LocalDate dataInclusao;
  int mes;
  int ano;

  public HistoricoSalarioDTO(double salarioCalculado, double bonusTotal, LocalDate dataInclusao, int mes, int ano) {
    this.salarioCalculado = salarioCalculado;
    this.bonusTotal = bonusTotal;
    this.dataInclusao = dataInclusao;
    this.mes = mes;
    this.ano = ano;
  }

  public HistoricoSalarioDTO(double valorEstatistica, LocalDate dataInclusao, int mes, int ano) {
    this.valorEstatistica = valorEstatistica;
    this.dataInclusao = dataInclusao;
    this.mes = mes;
    this.ano = ano;
  }

  public double getSalarioCalculado() {
    return salarioCalculado;
  }

  public double getBonusTotal() {
    return bonusTotal;
  }

  public double getValorEstatistica() {
    return valorEstatistica;
  }

  public LocalDate getDataInclusao() {
    return dataInclusao;
  }

  public int getMes() {
    return mes;
  }

  public int getAno() {
    return ano;
  }
}
