package gestaofuncionarios.presenter.funcionariopresenter.state;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import gestaofuncionarios.utils.DateUtils;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

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
        presenter.getView().getTxtCargo().setText(this.funcionario.getCargo());
        presenter.getView().getTxtNome().setText(this.funcionario.getNome());
        presenter.getView().getTxtFaltas().setText(String.valueOf(this.funcionario.getFaltas()));
        presenter.getView().getTxtIdade().setText(String.valueOf(this.funcionario.getIdade()));
        presenter.getView().getTxtSalarioBase()
                .setText(String.valueOf(presenter.getFormat().format(this.funcionario.getSalarioBase())));
        presenter.getView().getSeletorDataAdmissao().setDate(DateUtils.asDate(this.funcionario.getDataAdmissao()));
        presenter.getView().getTxtDistanciaTrabalho()
                .setText(String.valueOf(this.funcionario.getDistanciaDoTrabalho()));

        presenter.getView().getTxtCargo().setEditable(false);
        presenter.getView().getTxtNome().setEditable(false);
        presenter.getView().getTxtFaltas().setEditable(false);

        presenter.getView().getTxtIdade().setEditable(false);
        presenter.getView().getTxtSalarioBase().setEditable(false);
        presenter.getView().getTxtDistanciaTrabalho().setEditable(false);
        presenter.getView().getSeletorDataAdmissao().setEnabled(false);

        presenter.getView().getBtnSalvar().setVisible(false);
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
}
