package gestaofuncionarios.presenter.funcionariopresenter.state;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FuncionarioEditState extends FuncionarioPresenterState {

	Funcionario funcionarioEdit;
	private int idFuncionario;

	public FuncionarioEditState(FuncionarioPresenter presenter, Funcionario funcionario) {
		super(presenter);
		presenter.getView().setTitle("Editar Funcionario");
		idFuncionario = funcionario.getIdFuncionario();
		funcionarioEdit = funcionario;
		initaActionListener();
		getByIdFuncionario();
		configurarTela();
	}

	private void configurarTela() {
		presenter.getView().getBtnExcluir().setVisible(false);
		presenter.getView().getBtnEditar().setVisible(false);
		presenter.getView().getBtnSalvar().setVisible(true);

		presenter.getView().getTxtCargo().setEditable(true);
		presenter.getView().getTxtNome().setEditable(true);
		presenter.getView().getTxtFaltas().setEditable(true);

		presenter.getView().getTxtIdade().setEditable(true);
		presenter.getView().getTxtSalarioBase().setEditable(true);
		presenter.getView().getTxtDistanciaTrabalho().setEditable(true);
		presenter.getView().getSeletorDataAdmissao().setEnabled(true);
		presenter.getView().getcBoxFuncionarioMes().setEnabled(true);
	}

	@Override
	public void salvar() throws Exception {
		funcionarioEdit = lerDados();
		funcionarioEdit.setIdFuncionario(idFuncionario);
		presenter.getDao().update(funcionarioEdit);
		presenter.getView().dispose();
	}

	private void getByIdFuncionario() {
		try {
			this.funcionarioEdit = presenter.getDao().getById(funcionarioEdit.getIdFuncionario());
		} catch (Exception ex) {
			JOptionPane.showConfirmDialog(null, ex.getMessage());
		}
	}

	private void initaActionListener() {
		presenter.getView().getBtnSalvar().addActionListener((ActionEvent ae) -> {
			try {
				if (validarFuncionario()) {
					salvar();
				}
			} catch (Exception ex) {
				JOptionPane.showConfirmDialog(null, ex.getMessage());
			}
		});
	}

}
