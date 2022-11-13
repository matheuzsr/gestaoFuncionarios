package gestaofuncionarios.dados.dao;


import java.util.List;

import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.dto.HistoricoBonusFilterDTO;
import gestaofuncionarios.model.HistoricoBonus;

public interface HistoricoBonusDAO {
 
    public List<HistoricoBonusDTO> getHistoricoBonusByIdFuncionario(int idFuncionario) throws Exception;
    public List<HistoricoBonusDTO> getSerachHistoricoBonus(HistoricoBonusFilterDTO historicoBonusFilterDTO)  throws Exception;
    public void add(HistoricoBonus historicoBonus) throws Exception;
}
