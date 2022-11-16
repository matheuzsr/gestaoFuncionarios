package gestaofuncionarios.dados.dao;


import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.dto.HistoricoBonusFilterDTO;
import gestaofuncionarios.model.HistoricoBonus;

public interface HistoricoBonusDAO {
 
    public List<HistoricoBonusDTO> getHistoricoBonusByIdFuncionario(int idFuncionario) throws Exception;
    public List<HistoricoBonusDTO> getSerachHistoricoBonus(HistoricoBonusFilterDTO historicoBonusFilterDTO)  throws Exception;
    public HistoricoBonus getHistoricoBonusByData(LocalDate data, int idBonus,int idFuncionario) throws Exception;
    public void add(HistoricoBonus historicoBonus) throws Exception;
    public void update(HistoricoBonus historicoBonus) throws Exception;
    
}
