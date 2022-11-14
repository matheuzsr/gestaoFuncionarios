package gestaofuncionarios.observer.historicoSalario;

import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.model.HistoricoSalario;

public abstract class ObservableHistoricoSalario {

    protected List<ObserverHistoricoSalario> observerList = new ArrayList<>();

    public final void addObserver(ObserverHistoricoSalario observer) {
        observerList.add(observer);
    }

    public final void removerObserver(ObserverHistoricoSalario observer) {
        int indexObserver = observerList.indexOf(observer);
        if (indexObserver >= 0) {
            observerList.remove(observer);
        }
    }

    protected abstract void notificarObservers(List<HistoricoSalario> historicoSalarioList);
}
