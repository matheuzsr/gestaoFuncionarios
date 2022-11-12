package gestaofuncionarios.observer;

import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.model.Funcionario;

public abstract class Observable {

    protected List<Observer> observerList = new ArrayList<>();

    public final void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public final void removerObserver(Observer observer) {
        int indexObserver = observerList.indexOf(observer);
        if (indexObserver >= 0) {
            observerList.remove(observer);
        }
    }

    protected abstract void notificarObservers(List<Funcionario> funcionarios);
}
