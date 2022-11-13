package gestaofuncionarios.dados.dao;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.dto.HistoricoCalcularSalarioDTO;
import gestaofuncionarios.model.HistoricoSalario;

public interface HistoricoSalarioDAO {
    
    public List<HistoricoCalcularSalarioDTO> getAllHistoricoSalario() throws Exception;
    public void add(HistoricoSalario historicoBonus) throws Exception;
    public List<HistoricoCalcularSalarioDTO> getHistoricoSalarioByData(LocalDate date) throws Exception;

}
