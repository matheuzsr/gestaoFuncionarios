package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.model.Funcionario;

public class TipoBonusTempoServicoHandler extends TipoBonusHandler {

    public TipoBonusTempoServicoHandler() {
        super("tempo_servico", new BonusDAOSQLite());
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

    @Override
    public Double calcular(Funcionario funcionario) throws Exception {
        double bonusPercentage = getBonusPercentage(funcionario.getAnosTempoTrabalho());
        return funcionario.getSalarioBase() * bonusPercentage;
    }
}
