package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import gestaofuncionarios.dados.dao.BonusDAO;

import gestaofuncionarios.model.Funcionario;

public abstract class TipoBonusHandler {
    protected String tipo;
    
    TipoBonusHandler(String tipo, BonusDAO dao ){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
    public abstract Double calcular(Funcionario funcionario) throws Exception;
   
}
