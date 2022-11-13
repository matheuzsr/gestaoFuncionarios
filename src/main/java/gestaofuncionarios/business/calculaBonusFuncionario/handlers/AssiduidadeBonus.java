package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.model.Funcionario;

public class AssiduidadeBonus extends TipoBonusHandler {

    public AssiduidadeBonus() {
        super("bonus_assiduidade", new BonusDAOSQLite());
    }

    private double getBonusPercentage(int numeroFaltas) {
        // TODO: No docs não especifica se o 6 é incluso, perguntar ao professor
 
        if (numeroFaltas >= 4) {
            return 0.01;
        }
        if (numeroFaltas >= 1) {
            return 0.03;
        }
        if (numeroFaltas == 0) {
            return 0.05;
        }
        return 0;
    }

    @Override
    public Double calcular(Funcionario funcionario) throws Exception {
        return funcionario.getSalarioBase() * getBonusPercentage(funcionario.getFaltas());
    }

}
