package gestaofuncionarios.observer.funcionario;


import gestaofuncionarios.model.Funcionario;

import java.util.List;

public interface ObserverFuncionario {

    public void update(List<Funcionario> funcionarioList);

}
