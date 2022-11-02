package gestaofuncionarios.dados.dao;

import java.util.Collection;

import gestaofuncionarios.model.Funcionario;

public interface FuncionarioDAO {

    public void carregaFuncionarios();

    public boolean add(Funcionario funcionario) throws Exception;

    public boolean remove(String nome) throws Exception;

    public Funcionario getFuncionarioByName(String nome) throws Exception;

    public boolean contains(Funcionario funcionario);

    public Collection<Funcionario> getFuncionarios();

    // public void atualizarListaBonus(Funcionario funcionario) throws Exception;

    // public boolean altera(Funcionario funcionarioExistente, int IdAnterior)
    // throws Exception;

    // TODO: Possivelmente iremos precisar
    // public void addObserver(Observer observer);

    // public void notifyObservers();

}
