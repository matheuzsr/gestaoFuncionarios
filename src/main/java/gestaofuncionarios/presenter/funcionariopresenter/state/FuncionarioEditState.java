package gestaofuncionarios.presenter.funcionariopresenter.state;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.funcionariopresenter.FuncionarioPresenter;

public class FuncionarioEditState extends FuncionarioPresenterState {

    Funcionario funcionarioTemp;

    public FuncionarioEditState(FuncionarioPresenter presenter) {
        super(presenter);
        presenter.getView().setTitle("Editar Funcionario");

        configurarTela();
        String nome = presenter.getView().getTxtNome().getText();

        try {
            funcionarioTemp = presenter.getDao().getFuncionarioByName(nome);
        } catch (Exception ex) {
            Logger.getLogger(FuncionarioEditState.class.getName()).log(Level.SEVERE, null, ex);
        }

        presenter.getView().getBtnSalvar().addActionListener((ActionEvent ae) -> {
            try {
                salvar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage());
            }
        });
    }

    private void configurarTela() {
        presenter.getView().getBtnVerBonus().setVisible(false);
        presenter.getView().getBtnExcluir().setVisible(false);
        presenter.getView().getBtnEditar().setVisible(false);
        presenter.getView().getBtnSalvar().setVisible(true);

        presenter.getView().getTxtNome().setEditable(true);
        presenter.getView().getTxtCargo().setEditable(true);
        presenter.getView().getTxtSalarioBase().setEditable(true);
        presenter.getView().getTxtFaltas().setEditable(true);
        presenter.getView().getTxtDistancia().setEditable(true);
    }

    @Override
    public void salvar() throws Exception {
    }
}
