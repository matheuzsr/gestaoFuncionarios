package gestaofuncionarios.observer.funcionario;

import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.model.Funcionario;

public abstract class ObservableFuncionario {

    protected List<ObserverFuncionario> observerList = new ArrayList<>();

    public final void addObserver(ObserverFuncionario observer) {
        observerList.add(observer);
    }

    public final void removerObserver(ObserverFuncionario observer) {
        int indexObserver = observerList.indexOf(observer);
        if (indexObserver >= 0) {
            observerList.remove(observer);
        }
    }

    protected abstract void notificarObservers(List<Funcionario> funcionarios);
}
