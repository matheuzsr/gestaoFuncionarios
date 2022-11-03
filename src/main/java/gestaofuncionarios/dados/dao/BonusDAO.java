
package gestaofuncionarios.dados.dao;

import gestaofuncionarios.model.Bonus;

import java.util.Collection;

public interface BonusDAO {

    public Bonus getbyId(int id) throws Exception;

    public Collection<Bonus> getAllBonus() throws Exception;

}
