
package gestaofuncionarios.presenter;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.observer.Observer;
import gestaofuncionarios.view.GestaoFuncionariosView;
import io.github.cdimascio.dotenv.Dotenv;

/**
 *
 * @author logcomex
 */
public class GestaoFuncionariosPresenter implements Observer {
    private GestaoFuncionariosView view;
    private int totalFuncionarios;
    private FuncionarioDAOSQLite dao;
    private Dotenv dotenv;

    public GestaoFuncionariosPresenter(FuncionarioDAOSQLite dao, Dotenv dotenv) {
        this.view = new GestaoFuncionariosView();
        this.dao = dao;
        this.dotenv = dotenv;

        this.getTotalFuncionarios();
        this.atualizarTotalFuncionarios();
        this.initListterns();
    }

    private void initListterns() {
        this.view.getAddFuncionarioMenu().addActionListener((ActionEvent e) -> {
            try {
                FuncionarioPresenter presenter = new FuncionarioPresenter(dao, null);
                showPanel(presenter.getView(), false, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        });

        this.view.getBuscarFuncionariosMenu().addActionListener((ActionEvent e) -> {
            try {
                BuscarFuncionarioPresenter presenter = new BuscarFuncionarioPresenter(dao);

                dao.addObserver(presenter);

                showPanel(presenter.getView(), false, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        });

        this.view.getCalcularSalarioMenu().addActionListener((ActionEvent e) -> {
            try {
                CalculadoraSalarioPresenter presenter = new CalculadoraSalarioPresenter();

                showPanel(presenter.getView(), false, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        });

            this.view.getLblVersao().setText(this.dotenv.get("VERSION"));
            this.view.getLblArmazenamento().setText(this.dotenv.get("DB_NAME"));

        this.view.setVisible(true);

    }
    private void getTotalFuncionarios() {
        this.totalFuncionarios = dao.getAll().size();
    }

    private void atualizarTotalFuncionarios() {
        String qtd = String.valueOf(this.totalFuncionarios);

        this.view.getLblTotal().setText(qtd);
    }

  public static void showPanel(JInternalFrame frame, Boolean maximize, Boolean closable) throws Exception {

        // TODO: Voltar e Refatorar
        if (closable) {
            GestaoFuncionariosView.getDesktop().add(frame);
        } else {
            boolean isFrameShow = false;
            JInternalFrame[] allFrames = GestaoFuncionariosView.getDesktop().getAllFrames();

            for (JInternalFrame f : allFrames) {
                if (f.getTitle().equals(frame.getTitle())) {
                    isFrameShow = true;
                }
            }

            if (!isFrameShow) {
                GestaoFuncionariosView.getDesktop().remove(frame);
                GestaoFuncionariosView.getDesktop().add(frame);
            }
        }

        frame.setIconifiable(true);
        frame.setIcon(true);
        frame.setVisible(true);
        frame.setClosable(closable);
        frame.setMaximizable(true);
        frame.setResizable(true);
        if (!maximize) {
            frame.setIcon(false);
        } else {
            frame.setMaximum(true);
        }
        frame.moveToFront();
        frame.requestFocus();
    }

    @Override
    public void update(List<Funcionario> funcionarioList) {
        this.totalFuncionarios = funcionarioList.size();
        atualizarTotalFuncionarios();
    }
}