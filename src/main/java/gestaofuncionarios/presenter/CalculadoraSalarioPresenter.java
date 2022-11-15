package gestaofuncionarios.presenter;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gestaofuncionarios.business.CalculaSalarioFuncionario;
import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoSalarioDAOSQLite;
import gestaofuncionarios.dto.HistoricoCalcularSalarioDTO;
import gestaofuncionarios.utils.DateUtils;
import gestaofuncionarios.view.CalculadoraSalarioView;

public class CalculadoraSalarioPresenter {

	private CalculadoraSalarioView view;
	private CalculaSalarioFuncionario calcularSalrioFuncionario;
	private FuncionarioDAOSQLite funcionarioDAO;
	private DefaultTableModel tabelaCalculoSalario;
	private HistoricoSalarioDAOSQLite historicioSalarioDAOSQLite;

	public CalculadoraSalarioPresenter() {
		this.view = new CalculadoraSalarioView();
		this.calcularSalrioFuncionario = new CalculaSalarioFuncionario();
		funcionarioDAO = new FuncionarioDAOSQLite();
		historicioSalarioDAOSQLite = new HistoricoSalarioDAOSQLite();
		initConstruirTabela();
		initActrionListener();
	}

	public CalculadoraSalarioView getView() {
		return this.view;
	}

	public void CalcularSalario() {
		if (view.getSeletorDataCalculo().getDate() != null) {
			LocalDate dataEscolhida = DateUtils.asLocalDate(view.getSeletorDataCalculo().getDate());
			try {
				this.calcularSalrioFuncionario.calcularSalario(funcionarioDAO.getAll(), dataEscolhida, false);
				carregarTabela(historicioSalarioDAOSQLite.getHistoricoSalarioByData(dataEscolhida));
				this.view.getSeletorDataBusca().setDate(null);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}else {
			JOptionPane.showConfirmDialog(view, "Por favor escolha uma data para realizar o calculo");
		}
	}

	public void buscar() {
		if (view.getSeletorDataBusca().getDate() != null) {
			LocalDate dataEscolhida = DateUtils.asLocalDate(view.getSeletorDataBusca().getDate());
			try {
				carregarTabela(historicioSalarioDAOSQLite.getHistoricoSalarioByData(dataEscolhida));
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	public void listarTodos() {
		try {
			carregarTabela(historicioSalarioDAOSQLite.getAllHistoricoSalario());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	public void initActrionListener() {
		this.view.getBtnFechar().addActionListener((ActionEvent ae) -> {
			view.dispose();
		});

		this.view.getBtnCalcular().addActionListener((ActionEvent ae) -> {
			CalcularSalario();
		});

		this.view.getBtnBuscar().addActionListener((ActionEvent ae) -> {
			buscar();
		});

		this.view.getBtnListarTodos().addActionListener((ActionEvent ae) -> {
			listarTodos();
		});
	}

	private void initConstruirTabela() {
		tabelaCalculoSalario = new DefaultTableModel(new Object[][][][] {},
				new String[] { "Funcionario", "Data", "Salário base (R$)", "Bônus (R$)", "Salário (R$)" });
	}

	private void carregarTabela(Collection c) {
		tabelaCalculoSalario.setNumRows(0);

		DecimalFormat df = new DecimalFormat("0.00");

		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			HistoricoCalcularSalarioDTO HistoricoCalcularSalario = (HistoricoCalcularSalarioDTO) it.next();
			tabelaCalculoSalario.addRow(new Object[] { HistoricoCalcularSalario.getNomeFuncionario(),
					HistoricoCalcularSalario.getDataCalculo().format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
					df.format(HistoricoCalcularSalario.getValorSalarioBase()),
					df.format(HistoricoCalcularSalario.getValorBonus()),
					df.format(HistoricoCalcularSalario.getValorSalrio()) });
		}

		view.getTabelaFuncionarios().setModel(tabelaCalculoSalario);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		view.getTabelaFuncionarios().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		view.getTabelaFuncionarios().getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

	}

}
