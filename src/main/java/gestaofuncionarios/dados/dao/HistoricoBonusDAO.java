package gestaofuncionarios.dados.dao;

import gestaofuncionarios.model.HistoricoBonus;
import java.util.Collection;

public interface HistoricoBonusDAO {
 
    public Collection<HistoricoBonus> getAllBonus() throws Exception;
    
}
