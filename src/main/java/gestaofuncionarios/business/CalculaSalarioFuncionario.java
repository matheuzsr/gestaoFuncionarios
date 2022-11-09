package gestaofuncionarios.business;

import java.time.LocalDate;

import gestaofuncionarios.business.calculaBonusFuncionario.CalculaBonusProcessor;
import gestaofuncionarios.model.Funcionario;

public class CalculaSalarioFuncionario {
  LocalDate date = LocalDate.now();
  Funcionario funcionario;

  public CalculaSalarioFuncionario(Funcionario funcionario) {
    if (funcionario == null) {
      throw new RuntimeException("É necessário informar um funcionário para realizar o cálculo");
    }

    this.funcionario = funcionario;
  }

  public void calcular() {
    this.calularBonus();

    double valorBonusCalculado = this.getValorBonusCalculado(funcionario);
    funcionario.setSalario(funcionario.getSalarioBase() + valorBonusCalculado);
  }

  private void calularBonus() {
    new CalculaBonusProcessor().run(this.funcionario, this.date);
    // TODO: Salvar no banco o bonus do funcionario
  }

  private double getValorBonusCalculado(Funcionario funcionario) {
    // TODO: Pegar da base o count do bonus desse cara guardados no periodo
    // especifico
    // retorna o valor somatorio dos bonus
    return 0;
  }
}
