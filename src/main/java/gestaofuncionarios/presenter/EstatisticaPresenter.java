package gestaofuncionarios.presenter;

import gestaofuncionarios.business.calculoEstatistico.AbstractCalculoEstatisticoSalario;
import gestaofuncionarios.dados.dao.EstatisticaSalarioDAO;
import gestaofuncionarios.model.EstatisticaSalario;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.observer.Observer;
import gestaofuncionarios.utils.NumberUtils;
import gestaofuncionarios.view.EstatisticaView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EstatisticaPresenter implements Observer {

	private EstatisticaView view;
	private DefaultTableModel tabela;
	private AbstractCalculoEstatisticoSalario calculoEstatisticoSalario;

	public EstatisticaPresenter(AbstractCalculoEstatisticoSalario calculoEstatisticoSalario) {
		this.calculoEstatisticoSalario = calculoEstatisticoSalario;
		this.view = new EstatisticaView();

		this.criarTabela();

		List<EstatisticaSalario> calculoEstatisticoList = this.getCalculoEstatisticoList();

		this.carregarTabela(calculoEstatisticoList);
		this.initListterns();
	}

	private void initListterns() {
		view.getBtnFechar().addActionListener((ActionEvent e) -> {
			view.dispose();
		});
	}

	public EstatisticaView getView() {
		return this.view;
	}

	private void criarTabela() {
		tabela = new DefaultTableModel(new Object[][] {}, new String[] { "Resultado" }) {
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		tabela.addRow(new String[] { "Somatório" });
		tabela.addRow(new String[] { "Média" });
		tabela.addRow(new String[] { "Desvio padrão" });
		tabela.addRow(new String[] { "Maior salário" });
		tabela.addRow(new String[] { "Menor salário" });
		tabela.addRow(new String[] { "Qtd salários no mês" });
		tabela.addRow(new String[] { "Coef. variação" });
	}

	private void carregarTabela(List<EstatisticaSalario> estatisticaList) {

		for (int i = 0; i < estatisticaList.size(); i++) {
			this.setMesTabela(i + 1, estatisticaList.get(i));

		}

		view.getTblAtributos().setModel(tabela);
		view.getTblAtributos().getColumnModel().getColumn(0).setMinWidth(140);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		view.getTblAtributos().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	}

	private void setMesTabela(int position, EstatisticaSalario estatistica) {
		int mes = estatistica.getMes();
		int ano = estatistica.getAno();

		tabela.addColumn(Integer.toString(mes).concat("/").concat(Integer.toString(ano)));

		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getSomatorio()), 0, position);
		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getMedia()), 1, position);
		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getDesvioPadrao()), 2, position);
		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getMaiorSalario()), 3, position);
		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getMenorSalario()), 4, position);
		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getQtdSalariosMes()), 5, position);
		tabela.setValueAt(NumberUtils.formatDecimalLocale(estatistica.getCoeficienteVariacao()), 6, position);
	}

	private List<EstatisticaSalario> getCalculoEstatisticoList() {
		List<EstatisticaSalario> estatisticas = new ArrayList<>();
		try {
			estatisticas.addAll(this.calculoEstatisticoSalario.calcular());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}

		return estatisticas;
	}

	private void limparTabelaFuncionarios() {
	}

	@Override
	public void update(List<Funcionario> funcionarioList) {
		limparTabelaFuncionarios();
	}
}
