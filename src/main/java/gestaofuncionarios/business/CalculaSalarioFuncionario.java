package gestaofuncionarios.business;

import java.time.LocalDate;

import gestaofuncionarios.business.calculaBonusFuncionario.CalculaBonusProcessor;
import gestaofuncionarios.model.Funcionario;

public class CalculaSalarioFuncionario {
  public void calcular(Funcionario funcionario, LocalDate localDate) {
    new CalculaBonusProcessor().run(funcionario, localDate);
    double valorBonusCalculado = this.getValorBonusCalculado(funcionario, localDate);

    funcionario.setSalario(funcionario.getSalarioBase() + valorBonusCalculado);
  }

  private double getValorBonusCalculado(Funcionario funcionario, LocalDate localDate) {
    // TODO: Pegar da base o count do bonus desse cara guardados no periodo
    // especifico
    // retorna o valor
    return 0;
  }
}
