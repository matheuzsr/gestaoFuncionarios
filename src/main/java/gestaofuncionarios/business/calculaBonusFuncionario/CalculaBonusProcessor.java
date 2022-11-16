package gestaofuncionarios.business.calculaBonusFuncionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TipoBonusAssiduidadeHandler;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TipoBonusFuncionarioMesHandler;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TipoBonusHandler;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TipoBonusTempoServicoHandler;
import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.dto.TipoBonusRecebidoDTO;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.model.HistoricoBonus;

public class CalculaBonusProcessor {

    private HistoricoBonusDAOSQLite historicoBonusDAO;
    private BonusDAOSQLite bonusDAO;

    public CalculaBonusProcessor(HistoricoBonusDAOSQLite historicoBonusDAO, BonusDAOSQLite bonusDAO) {
        this.historicoBonusDAO = historicoBonusDAO;
        this.bonusDAO = bonusDAO;
    }

    public Funcionario run(Funcionario funcionario, LocalDate localDate ) throws Exception {
        Double valorTotalBonus = 0.0;
        List<TipoBonusRecebidoDTO> tipoBonusRecebidos = new ArrayList<>();
        TipoBonusHandler tipoBOnus = initFilaHandle();
        
        tipoBonusRecebidos = tipoBOnus.handleRequest(valorTotalBonus, funcionario,tipoBonusRecebidos);
        for(TipoBonusRecebidoDTO tipoBonus : tipoBonusRecebidos ) {
            insertHistoricoBonus(funcionario.getIdFuncionario(), tipoBonus.getTipoBonus(), localDate, tipoBonus.getValor());	
            valorTotalBonus += tipoBonus.getValor();
        }
        
        funcionario.setSalario(funcionario.getSalarioBase() + valorTotalBonus);
        return funcionario;
    }

    private void insertHistoricoBonus(int idFuncionario, String tipoBonus, LocalDate localDate, Double valorBonus)
            throws Exception {
        historicoBonusDAO.add(new HistoricoBonus(idFuncionario, getIdBonusByTipo(tipoBonus), localDate, valorBonus));
    }

    private int getIdBonusByTipo(String tipoBouns) throws Exception {
        return bonusDAO.getIdByNome(tipoBouns);
    }
    
   private TipoBonusHandler initFilaHandle() throws Exception {
	   TipoBonusAssiduidadeHandler assiduidadeBonus  =new TipoBonusAssiduidadeHandler();
       TipoBonusTempoServicoHandler tempoServicoHandler = new TipoBonusTempoServicoHandler();
       TipoBonusFuncionarioMesHandler funcionarioMes = new TipoBonusFuncionarioMesHandler();
       
       assiduidadeBonus.setNext(tempoServicoHandler);
       tempoServicoHandler.setNext(funcionarioMes);
       
       
       return assiduidadeBonus; 
   	}
    
    
}
