package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.model.Funcionario;

public class FuncionarioMes extends TipoBonusHandler {

    public FuncionarioMes() {
        super("funcionario_mes", new BonusDAOSQLite());
    }

    @Override
    public Double calcular(Funcionario funcionario) throws Exception {
       return funcionario.isFuncionarioMes() ? 500.00 : 0.0;
    }
}
