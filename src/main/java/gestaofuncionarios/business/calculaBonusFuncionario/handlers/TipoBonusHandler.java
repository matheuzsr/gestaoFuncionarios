package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.util.List;

import gestaofuncionarios.dados.dao.BonusDAO;
import gestaofuncionarios.dto.TipoBonusRecebidoDTO;
import gestaofuncionarios.model.Funcionario;

public abstract class TipoBonusHandler implements IHandler{
    protected String tipo;
    private IHandler next;
    
    @Override
    public void setNext(IHandler handler) {
      	next = handler;
    }
    
    @Override
    public List<TipoBonusRecebidoDTO> handleRequest(Double valor, Funcionario funcionario, List<TipoBonusRecebidoDTO> tipoBonusRecebidos) throws Exception{
    	TipoBonusRecebidoDTO tipoBonus = new TipoBonusRecebidoDTO();
    	tipoBonus.setTipoBonus(tipo);
    	tipoBonus.setValor(calcular(funcionario));
    	tipoBonusRecebidos.add(tipoBonus);
    	if(this.next != null) {
    		return this.next.handleRequest(valor, funcionario, tipoBonusRecebidos);	
    	}
    	return tipoBonusRecebidos;
    }
    
    TipoBonusHandler(String tipo, BonusDAO dao ){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
    public abstract Double calcular(Funcionario funcionario) throws Exception;
   
}
