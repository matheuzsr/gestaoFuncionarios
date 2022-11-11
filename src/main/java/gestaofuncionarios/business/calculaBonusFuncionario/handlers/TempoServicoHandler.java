package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.model.Bonus;
import gestaofuncionarios.model.Funcionario;

public class TempoServicoHandler extends BonusHandler {

    public TempoServicoHandler() {
        super("tempo_servico");
    }

    @Override
    public void calcular(Funcionario funcionario, LocalDate localDate, List<Bonus> bonusAplicadosList)
            throws Exception {
        double bonusPercentage = getBonusPercentage(funcionario.getAnosTempoTrabalho());
        double valor = funcionario.getSalarioBase() * bonusPercentage;

        bonusAplicadosList.add(new Bonus(this.tipo, valor, localDate));
    }

    private double getBonusPercentage(int qtdAnosServico) {
        if (qtdAnosServico >= 20) {
            return 0.15;
        }
        if (qtdAnosServico >= 16) {
            return 0.1;
        }
        if (qtdAnosServico >= 11) {
            return 0.08;
        }
        if (qtdAnosServico >= 6) {
            return 0.03;
        }
        if (qtdAnosServico >= 1) {
            return 0.02;
        }

        return 0;
    }

}
