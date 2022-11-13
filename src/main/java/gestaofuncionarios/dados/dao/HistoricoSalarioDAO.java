package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dto.HistoricoCalcularSalarioDTO;
import gestaofuncionarios.model.HistoricoSalario;
import java.util.Collection;

public interface HistoricoSalarioDAO {
    
    public Collection<HistoricoCalcularSalarioDTO> getAllHistoricoBonus() throws Exception;
    public void add(HistoricoSalario historicoBonus) throws Exception;
}
