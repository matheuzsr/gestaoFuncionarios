package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.model.Funcionario;

public class TipoBonusFuncionarioMesHandler extends TipoBonusHandler {

    public TipoBonusFuncionarioMesHandler() {
        super("funcionario_mes", new BonusDAOSQLite());
    }

    @Override
    public Double calcular(Funcionario funcionario) throws Exception {
       return funcionario.isFuncionarioMes() ? funcionario.getSalario() * 1.5 : 0.0;
    }

}
