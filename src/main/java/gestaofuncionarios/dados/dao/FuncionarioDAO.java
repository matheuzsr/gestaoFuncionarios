package gestaofuncionarios.dados.dao;

import java.util.Collection;
import java.util.List;

import gestaofuncionarios.model.Funcionario;

public interface FuncionarioDAO {

    public boolean add(Funcionario funcionario) throws Exception;

    public boolean delete(int idFuncionario) throws Exception;

    public List<Funcionario> getFuncionariosByName(String nome) throws Exception;

    public boolean contains(Funcionario funcionario);

    public Funcionario getById(int searchId) throws Exception;

    public Collection<Funcionario> getAll() throws Exception;
    
    public void update(Funcionario funcionario) throws Exception;
}
