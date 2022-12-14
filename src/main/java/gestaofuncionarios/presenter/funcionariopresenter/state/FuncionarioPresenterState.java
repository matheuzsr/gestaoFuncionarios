package gestaofuncionarios.presenter.funcionariopresenter.state;

import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.FuncionarioPresenter;
import gestaofuncionarios.utils.DateUtils;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public abstract class FuncionarioPresenterState {

	protected FuncionarioPresenter presenter;

	public FuncionarioPresenterState(FuncionarioPresenter presenter) {
		this.presenter = presenter;
		limpaListeners();
	}

	public void salvar() throws Exception {
	}

	public void editar() throws Exception {
	}

	public void excluir() throws Exception {
	}

	public void cancelar() {
	}

	public final void fechar() {
		this.presenter.getView().setVisible(false);
		this.presenter.getView().dispose();
	}

	private void limpaListeners() {

		for (Component c : this.presenter.getView().getContentPane().getComponents()) {
			if (c instanceof JButton) {
				for (ActionListener al : ((JButton) c).getActionListeners()) {
					((JButton) c).removeActionListener(al);
				}
			}
		}
	}

	protected Funcionario lerDados() throws Exception {
		String nome = presenter.getView().getTxtNome().getText();
		String cargo = presenter.getView().getTxtCargo().getText();
		double salarioBase = 0;
		if (!presenter.getView().getTxtSalarioBase().getText().isEmpty()) {
			salarioBase = Double.parseDouble(presenter.getView().getTxtSalarioBase().getText().replace(",", "."));
		}

		int idade = 0;
		if (!presenter.getView().getTxtIdade().getText().isEmpty()) {
			idade = Integer.parseInt(presenter.getView().getTxtIdade().getText());
		}

		Funcionario f = new Funcionario(nome, LocalDate.now().minusYears(idade), cargo, salarioBase);

		if (!presenter.getView().getTxtFaltas().getText().isEmpty()) {
			int faltas = Integer.parseInt(presenter.getView().getTxtFaltas().getText());
			f.setFaltas(faltas);
		}

		if (!presenter.getView().getTxtDistanciaTrabalho().getText().isEmpty()) {
			double distancia = Double.parseDouble(presenter.getView().getTxtDistanciaTrabalho().getText());
			f.setDistanciaDoTrabalho(distancia);
		}

		if (presenter.getView().getSeletorDataAdmissao().getDate() != null) {
			LocalDate dataAdmissao = DateUtils.asLocalDate(presenter.getView().getSeletorDataAdmissao().getDate());
			f.setDataAdmissao(dataAdmissao);
		}

		f.setFuncionarioMes(presenter.getView().getcBoxFuncionarioMes().isSelected());

		return f;
	}

	public boolean validarFuncionario() {
		if (presenter.getView().getTxtNome().getText().isEmpty()) {
			JOptionPane.showMessageDialog(presenter.getView(), "Nome n??o pode ser vazio");
			return false;
		}
		;

		if (presenter.getView().getTxtCargo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(presenter.getView(), "Cargo n??o pode ser vazio");
			return false;
		}
		;

		if (presenter.getView().getTxtSalarioBase().getText().isEmpty()) {
			JOptionPane.showMessageDialog(presenter.getView(), "Salario Base n??o pode salario");
			return false;
		} else if (Double.parseDouble(presenter.getView().getTxtSalarioBase().getText().replace(",", ".")) < 0) {
			JOptionPane.showMessageDialog(presenter.getView(), "Salario Base n??o pode ter um valor negativo");
			return false;
		}

		if (presenter.getView().getTxtIdade().getText().isEmpty()) {
			JOptionPane.showMessageDialog(presenter.getView(), "Idade n??o pode ser vazio");
			return false;
		}

		if (presenter.getView().getTxtFaltas().getText().isEmpty()) {
			JOptionPane.showMessageDialog(presenter.getView(), "Numero de Faltas n??o pode ser vazio");
			return false;
		}

		if (presenter.getView().getTxtDistanciaTrabalho().getText().isEmpty()) {
			JOptionPane.showMessageDialog(presenter.getView(), "Distancia do Trabalho n??o pode ser vazio");
			return false;
		}

		if (presenter.getView().getSeletorDataAdmissao().getDate() == null) {
			JOptionPane.showMessageDialog(presenter.getView(), "Data de admiss??o n??o pode ser vazio");
			return false;
		}

		return true;
	}
}
