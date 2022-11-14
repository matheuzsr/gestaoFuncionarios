package gestaofuncionarios.observer.historicoSalario;

import gestaofuncionarios.model.HistoricoSalario;

import java.util.List;

public interface ObserverHistoricoSalario {

    public void update(List<HistoricoSalario> historicoSalarioList);

}
