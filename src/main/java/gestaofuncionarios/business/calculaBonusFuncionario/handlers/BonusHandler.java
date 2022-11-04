package gestaofuncionarios.business.calculaBonusFuncionario.handlers;

import java.time.LocalDate;
import java.util.List;

import gestaofuncionarios.model.Bonus;
import gestaofuncionarios.model.Funcionario;

public abstract class BonusHandler {
    protected String tipo;

    BonusHandler(String tipo) {
        this.tipo = tipo;
    }

    public abstract void calcular(Funcionario funcionario, LocalDate localDate, List<Bonus> bonusAplicadosList) throws Exception;
}
