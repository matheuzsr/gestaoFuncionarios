package gestaofuncionarios.presenter.funcionariopresenter.state;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FuncionarioEditState extends FuncionarioPresenterState {

    Funcionario funcionarioEdit;

    public FuncionarioEditState(FuncionarioPresenter presenter, Funcionario funcionario) {
        super(presenter);
        presenter.getView().setTitle("Editar Funcionario");

        configurarTela();
        try {
            this.funcionarioEdit = presenter.getDao().getById(funcionario.getIdFuncionario());
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
        presenter.getView().getBtnExcluir().setVisible(false);
        presenter.getView().getBtnEditar().setVisible(false);
        presenter.getView().getBtnSalvar().setVisible(true);

        presenter.getView().getTxtNome().setEditable(true);
    }

    @Override
    public void salvar() throws Exception {
    }
}
