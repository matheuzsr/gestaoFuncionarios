package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.model.Bonus;
import gestaofuncionarios.model.Funcionario;

public class AssiduidadeBonus extends BonusHandler {

    public AssiduidadeBonus() {
        super("bonus_assiduidade");
    }

    @Override
    public void calcular(Funcionario funcionario, LocalDate localDate, List<Bonus> bonusAplicadosList)
            throws Exception {
        double bonusPercentage = getBonusPercentage(funcionario.getFaltas());
        double valor = funcionario.getSalarioBase() * bonusPercentage;

        bonusAplicadosList.add(new Bonus(this.tipo, valor, localDate));
    }

    private double getBonusPercentage(int numeroFaltas) throws Exception {
        // TODO: No docs não especifica se o 6 é incluso, perguntar ao professor
        if (numeroFaltas >= 6) {
            return 0;
        }

        if (numeroFaltas >= 4) {
            return 0.01;
        }

        if (numeroFaltas >= 1) {
            return 0.03;
        }
        if (numeroFaltas == 0) {
            return 0.05;
        }

        throw new Exception("Para realizar o cálculo o número de faltas deve ser maior que zero!");
    }

}
