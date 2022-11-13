package gestaofuncionarios.business.calculaBonusFuncionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gestaofuncionarios.business.calculaBonusFuncionario.handlers.AssiduidadeBonus;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TipoBonusHandler;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.FuncionarioMes;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TempoServicoHandler;
import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.model.HistoricoBonus;

public class CalculaBonusProcessor {

    private HistoricoBonusDAOSQLite historicoBonusDAO;
    private BonusDAOSQLite bonusDAO;
    private Double ValorTotalRecebido;

    public CalculaBonusProcessor(HistoricoBonusDAOSQLite historicoBonusDAO, BonusDAOSQLite bonusDAO) {
        this.historicoBonusDAO = historicoBonusDAO;
        this.bonusDAO = bonusDAO;
    }

    public Funcionario run(Funcionario funcionario, LocalDate localDate, Boolean isAtualizar) throws Exception {
        List<String> TiposBonusList = new ArrayList<>();
        Double valorTotalBonus = 0.0;
        List<TipoBonusHandler> handlersList = new ArrayList<>(Arrays.asList(
                new AssiduidadeBonus(),
                new TempoServicoHandler(),
                new FuncionarioMes()));

        for (TipoBonusHandler handler : handlersList) {
            Double valorBonus = handler.calcular(funcionario);
            valorTotalBonus += valorBonus;
            if (isAtualizar) {
                InsertHistoricoBonus(funcionario.getIdFuncionario(), handler.getTipo(), localDate, handler.calcular(funcionario));
            } else {
                InsertHistoricoBonus(funcionario.getIdFuncionario(), handler.getTipo(), localDate, handler.calcular(funcionario));
            }
        }
        funcionario.setSalario(funcionario.getSalarioBase() + valorTotalBonus);
        return funcionario;
    }

    private void InsertHistoricoBonus(int idFuncionario, String tipoBonus, LocalDate localDate, Double valorBonus) throws Exception {
        historicoBonusDAO.add(new HistoricoBonus(idFuncionario, getIdBonusByTipo(tipoBonus), localDate,valorBonus));
    }

    private int getIdBonusByTipo(String tipoBouns) throws Exception {
        return bonusDAO.getIdByNome(tipoBouns);
    }

}
