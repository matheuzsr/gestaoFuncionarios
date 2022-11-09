
package gestaofuncionarios.presenter;

import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import gestaofuncionarios.dados.dao.FuncionarioDAO;
import gestaofuncionarios.view.GestaoFuncionariosView;

/**
 *
 * @author logcomex
 */
public class GestaoFuncionariosPresenter {
    private GestaoFuncionariosView view;

    public GestaoFuncionariosPresenter(FuncionarioDAO dao) {
        this.view = new GestaoFuncionariosView();
        this.atualizarTotalFuncionarios(dao);

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
                showPanel(presenter.getView(), false, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        });

        this.view.setVisible(true);
    }

    private void atualizarTotalFuncionarios(FuncionarioDAO dao) {
        String qtd = String.valueOf(dao.getFuncionarios().size());
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
}