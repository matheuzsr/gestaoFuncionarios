package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dto.HistoricoSalarioDTO;

import java.util.List;

public interface EstatisticaSalarioDAO {
    public List<HistoricoSalarioDTO> getAllMeses(String operacao) throws Exception;
}
