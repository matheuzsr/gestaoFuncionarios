
package gestaofuncionarios;

import gestaofuncionarios.dados.dao.EstatisticaSalarioDAO;
import gestaofuncionarios.dados.dao.EstatisticaSalarioDAOSQLite;
import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.presenter.GestaoFuncionariosPresenter;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.JOptionPane;

public class Application {

    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.configure().load();

        try {

            FuncionarioDAOSQLite funcionarioDAO = new FuncionarioDAOSQLite();
            EstatisticaSalarioDAO estatisticaSalarioDAO = new EstatisticaSalarioDAOSQLite();

            GestaoFuncionariosPresenter presenter = new GestaoFuncionariosPresenter(
                    funcionarioDAO,
                    estatisticaSalarioDAO,
                    dotenv);

            funcionarioDAO.addObserver(presenter);

        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e.getStackTrace());
        }
    }
}
