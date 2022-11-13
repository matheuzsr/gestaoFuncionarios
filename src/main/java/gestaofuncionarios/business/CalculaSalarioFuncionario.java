package gestaofuncionarios.business;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.business.calculaBonusFuncionario.CalculaBonusProcessor;
import gestaofuncionarios.dados.dao.BonusDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoSalarioDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.model.HistoricoSalario;

public class CalculaSalarioFuncionario {

    private CalculaBonusProcessor calculoBonusProcessor;
    private HistoricoSalarioDAOSQLite historicioSalarioDAO;
    public CalculaSalarioFuncionario() {
        this.calculoBonusProcessor = new CalculaBonusProcessor(new HistoricoBonusDAOSQLite(), new BonusDAOSQLite());
        historicioSalarioDAO = new HistoricoSalarioDAOSQLite();
    }
    
   
    public void calcularSalario(List<Funcionario> listFuncionario, LocalDate date, Boolean isAtualizar) throws Exception{
        for (Funcionario funcionario : listFuncionario) {
          funcionario = this.calculoBonusProcessor.run(funcionario, date, isAtualizar);
          insertHistoricoSalario(funcionario.getIdFuncionario(),funcionario.getTotalBonus(),funcionario.getSalario(),date);
        }
    }
    public void insertHistoricoSalario(int idFuncionario, Double ValorTotalBonus, Double ValorTotalSalario, LocalDate date) throws Exception {
    	historicioSalarioDAO.add(new HistoricoSalario(idFuncionario, ValorTotalBonus, ValorTotalSalario, date));
    }	
    
}
