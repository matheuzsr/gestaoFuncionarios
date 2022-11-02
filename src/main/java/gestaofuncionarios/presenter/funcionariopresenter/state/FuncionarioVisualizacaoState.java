package gestaofuncionarios.presenter.funcionariopresenter.state;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.funcionariopresenter.FuncionarioPresenter;

public class FuncionarioVisualizacaoState extends FuncionarioPresenterState {

    private final Funcionario funcionario;

    public FuncionarioVisualizacaoState(FuncionarioPresenter presenter) {
        super(presenter);
        presenter.getView().setTitle("Visualizar Funcionario");
        this.funcionario = presenter.getFuncionario();
        configurarTela(presenter);
        configurarListeners(presenter);

    }

    private void configurarTela(FuncionarioPresenter presenter) {
        presenter.getView().getTxtNome().setText(this.funcionario.getNome());
        presenter.getView().getTxtNome().setEditable(false);
        presenter.getView().getTxtCargo().setText(this.funcionario.getCargo());
        presenter.getView().getTxtCargo().setEditable(false);
        presenter.getView().getTxtSalarioBase()
                .setText(String.valueOf(presenter.getFormat().format(this.funcionario.getSalarioBase())));
        presenter.getView().getTxtSalarioBase().setEditable(false);
        presenter.getView().getTxtFaltas().setText(String.valueOf(this.funcionario.getFaltas()));
        presenter.getView().getTxtFaltas().setEditable(false);
        presenter.getView().getTxtDistancia().setText(String.valueOf(this.funcionario.getDistanciaDoTrabalho()));
        presenter.getView().getTxtDistancia().setEditable(false);
    }

    private void configurarListeners(FuncionarioPresenter presenter) {
        presenter.getView().getBtnEditar().addActionListener((ActionEvent ae) -> {
            editar();
        });

        presenter.getView().getBtnExcluir().addActionListener((ActionEvent ae) -> {
            try {
                excluir();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage());
            }
        });
        presenter.getView().getBtnVerBonus().addActionListener((ActionEvent ae) -> {
            try {
                // visualizarBonus();
            } catch (Exception ex) {
                Logger.getLogger(FuncionarioVisualizacaoState.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        presenter.getView().getBtnFechar().addActionListener((ActionEvent ae) -> {
            presenter.getView().dispose();
        });
    }

    @Override
    public void editar() {
        presenter.setEstado(new FuncionarioEditState(presenter));
    }

    @Override
    public void excluir() throws Exception {
        String nome = presenter.getView().getTxtNome().getText();

        int showConfirmDialog = JOptionPane.showConfirmDialog(presenter.getView(), "Exluir");

        if (showConfirmDialog == 0) {
            presenter.getDao().remove(nome);
            JOptionPane.showMessageDialog(null, nome + " excluido");
            presenter.getView().dispose();
        }
    }

    // private void visualizarBonus() throws Exception {
    // BonusRecebidosPresenter presenterBonus = new
    // BonusRecebidosPresenter(this.funcionario);
    // SistemaGestaoRHPresenter.activePanel(presenterBonus.getView(), false, false);
    // }

}
