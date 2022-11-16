package gestaofuncionarios.presenter.funcionariopresenter.state;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import gestaofuncionarios.utils.DateUtils;

public class FuncionariosAddState extends FuncionarioPresenterState {

	public FuncionariosAddState(FuncionarioPresenter presenter) {
		super(presenter);

		presenter.getView().setTitle("Adicionar Funcionário");
		presenter.getView().getTxtCargo().setText("");
		presenter.getView().getTxtDistanciaTrabalho().setText("");
		presenter.getView().getTxtFaltas().setText("");
		presenter.getView().getTxtNome().setText("");
		presenter.getView().getTxtSalarioBase().setText("");
		presenter.getView().getSeletorDataAdmissao().setDate(DateUtils.asDate(LocalDate.now()));
		presenter.getView().getBtnExcluir().setVisible(false);
		presenter.getView().getBtnEditar().setVisible(false);
		presenter.getView().getBtnSalvar().setVisible(true);
		presenter.getView().getBtnSalvar().addActionListener((ActionEvent ae) -> {
			try {

				if (validarFuncionario()) {
					salvar();
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage());
			}
		});

		initActionListener();

		presenter.getView().setVisible(true);
	}

	@Override
	public void salvar() throws Exception {
		Funcionario funcionario = lerDados();
		if (presenter.getDao().add(funcionario)) {
			JOptionPane.showMessageDialog(presenter.getView(),
					"Funcionario " + funcionario.getNome() + " salvo com sucesso!");
			this.fechar();
		} else {
			JOptionPane.showMessageDialog(presenter.getView(), "Erro ao salvar no banco de dados!");
		}
	}

	private void initActionListener() {
		presenter.getView().getBtnFechar().addActionListener((ActionEvent ae) -> {
			this.fechar();
		});
	}

}
