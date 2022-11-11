
package gestaofuncionarios;

import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.presenter.GestaoFuncionariosPresenter;
import io.github.cdimascio.dotenv.Dotenv;

public class Application {

    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.configure().load();

        try {
            FuncionarioDAOSQLite funcionarioDAO = new FuncionarioDAOSQLite();
            GestaoFuncionariosPresenter presenter = new GestaoFuncionariosPresenter(funcionarioDAO, dotenv);

            funcionarioDAO.addObserver(presenter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
