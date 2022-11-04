package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.model.Bonus;
import gestaofuncionarios.model.Funcionario;

public class FuncionarioMes extends BonusHandler {

    public FuncionarioMes() {
        super("bonus_funcionario_mes");
    }

    @Override
    public void calcular(Funcionario funcionario, LocalDate localDate, List<Bonus> bonusAplicadosList)
            throws Exception {
        if (funcionario.isFuncionarioMes()) {
            bonusAplicadosList.add(new Bonus(this.tipo, 500.00, localDate));

        }
    }

}
