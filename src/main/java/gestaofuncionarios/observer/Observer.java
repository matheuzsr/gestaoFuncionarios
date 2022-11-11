package gestaofuncionarios.observer;


import gestaofuncionarios.model.Funcionario;

import java.util.List;

public interface Observer {

    public void update(List<Funcionario> funcionarioList);

}
