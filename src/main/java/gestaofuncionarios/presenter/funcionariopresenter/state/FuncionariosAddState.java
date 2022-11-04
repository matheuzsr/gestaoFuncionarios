package gestaofuncionarios.presenter.funcionariopresenter.state;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class FuncionariosAddState extends FuncionarioPresenterState {

    public FuncionariosAddState(FuncionarioPresenter presenter) {
        super(presenter);
        presenter.getView().setTitle("Adicionar Funcionário");
        presenter.getView().getTxtCargo().setText("");
        presenter.getView().getTxtDistanciaTrabalho().setText("");
        presenter.getView().getTxtFaltas().setText("");
        presenter.getView().getTxtNome().setText("");
        presenter.getView().getTxtSalarioBase().setText("");
        presenter.getView().getBtnExcluir().setVisible(false);
        presenter.getView().getBtnEditar().setVisible(false);
        presenter.getView().getBtnSalvar().setVisible(true);
        presenter.getView().getBtnSalvar().addActionListener((ActionEvent ae) -> {
            try {
                salvar();
                fechar();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage());
            }
        });
        presenter.getView().getBtnFechar().addActionListener((ActionEvent ae) -> {
            fechar();
        });

        presenter.getView().setVisible(true);
    }

    @Override
    public void salvar() throws Exception {
        Funcionario funcionario = lerDados();
        if (presenter.getDao().add(funcionario)) {
            JOptionPane.showMessageDialog(presenter.getView(), "Funcionario " + funcionario.getNome() + " salvo com sucesso!");
        }

        presenter.getView().setVisible(false);

    }

}
