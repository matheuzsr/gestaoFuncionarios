package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.util.List;

import gestaofuncionarios.dto.TipoBonusRecebidoDTO;
import gestaofuncionarios.model.Funcionario;

public interface IHandler {
    
    public void setNext(IHandler handler);
    public List<TipoBonusRecebidoDTO> handleRequest(Double valor, Funcionario funcionario, List<TipoBonusRecebidoDTO> tipoBonusRecebidos) throws Exception;
}
