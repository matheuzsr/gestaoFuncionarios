package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.model.Bonus;
import gestaofuncionarios.model.Funcionario;

public class DistanciaTrabalho extends BonusHandler {

    public DistanciaTrabalho() {
        super("bonus_distancia_trabalho");
    }

    @Override
    public void calcular(Funcionario funcionario, LocalDate localDate, List<Bonus> bonusAplicadosList)
            throws Exception {
        double bonusPercentage = getBonusPercentage(funcionario.getAnosTempoTrabalho());
        double valor = funcionario.getSalarioBase() * bonusPercentage;

        bonusAplicadosList.add(new Bonus(this.tipo, valor, localDate));
    }

    private double getBonusPercentage(int yearsOfService) {
        if (yearsOfService >= 20) {
            return 0.15;
        }
        if (yearsOfService >= 16) {
            return 0.1;
        }
        if (yearsOfService >= 11) {
            return 0.08;
        }
        if (yearsOfService >= 6) {
            return 0.03;
        }
        if (yearsOfService >= 1) {
            return 0.02;
        }

        return 0;
    }

}
