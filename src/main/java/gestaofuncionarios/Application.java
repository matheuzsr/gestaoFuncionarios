
package gestaofuncionarios;

import gestaofuncionarios.business.calculoEstatistico.CalculoEstatisticoSalario;
import gestaofuncionarios.dados.dao.EstatisticaSalarioDAO;
import gestaofuncionarios.dados.dao.EstatisticaSalarioDAOSQLite;
import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoSalarioDAO;
import gestaofuncionarios.dados.dao.HistoricoSalarioDAOSQLite;
import gestaofuncionarios.presenter.BuscarFuncionarioPresenter;
import gestaofuncionarios.presenter.CalculadoraSalarioPresenter;
import gestaofuncionarios.presenter.EstatisticaPresenter;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import gestaofuncionarios.presenter.GestaoFuncionariosPresenter;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.JOptionPane;

public class Application {

    public static void main(String[] args) throws Exception {
        Dotenv dotenv = Dotenv.configure().load();

        try {

            FuncionarioDAOSQLite funcionarioDAO = new FuncionarioDAOSQLite();
            EstatisticaSalarioDAO estatisticaSalarioDAO = new EstatisticaSalarioDAOSQLite();
            HistoricoSalarioDAOSQLite historicoSalarioDAO = new HistoricoSalarioDAOSQLite();

            CalculoEstatisticoSalario calculoEstatisticoSalario = new CalculoEstatisticoSalario(estatisticaSalarioDAO);

            FuncionarioPresenter funcionarioPresenter = new FuncionarioPresenter(funcionarioDAO, null);
            BuscarFuncionarioPresenter buscarFuncionarioPresenter = new BuscarFuncionarioPresenter(funcionarioDAO);
            CalculadoraSalarioPresenter calculadoraSalarioPresenter = new CalculadoraSalarioPresenter(
                    historicoSalarioDAO);
            EstatisticaPresenter estatisticaPresenter = new EstatisticaPresenter(calculoEstatisticoSalario);

            GestaoFuncionariosPresenter gestaoFuncionariosPresenter = new GestaoFuncionariosPresenter(
                    funcionarioDAO,
                    funcionarioPresenter,
                    buscarFuncionarioPresenter,
                    calculadoraSalarioPresenter,
                    estatisticaPresenter,
                    dotenv);

            funcionarioDAO.addObserver(gestaoFuncionariosPresenter);
            funcionarioDAO.addObserver(buscarFuncionarioPresenter);
            historicoSalarioDAO.addObserver(estatisticaPresenter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
        }
    }
}
