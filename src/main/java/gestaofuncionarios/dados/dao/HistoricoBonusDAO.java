package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.model.HistoricoBonus;
import java.util.Collection;

public interface HistoricoBonusDAO {
 
    public Collection<HistoricoBonusDTO> getHistoricoBonusByIdFuncionario(int idFuncionario) throws Exception;
    public Collection<HistoricoBonusDTO> getHistoricoBonusByNameFuncionario(String NameFuncionario) throws Exception;
    public void add(HistoricoBonus historicoBonus) throws Exception;
}
