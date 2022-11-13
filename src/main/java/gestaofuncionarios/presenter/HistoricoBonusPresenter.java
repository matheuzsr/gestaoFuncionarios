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

import gestaofuncionarios.dados.dao.HistoricoBonusDAO;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.dto.HistoricoBonusFilterDTO;
import gestaofuncionarios.utils.DateUtils;
import gestaofuncionarios.view.HistoricoBonusView;

public class HistoricoBonusPresenter {

	private final HistoricoBonusView view;
	private HistoricoBonusDAO dao;
	private DefaultTableModel tabelaBonus;
	private int idFuncionario;

	public HistoricoBonusPresenter(int id) throws Exception {
		idFuncionario = id;
		this.view = new HistoricoBonusView();
		dao = new HistoricoBonusDAOSQLite();
		initConstruirTabela();

		if (id > 0) {
			getHistoricoById(idFuncionario);
		}

		initActrionListener();

		this.view.setIconifiable(true);
		this.view.setIcon(true);
		this.view.setVisible(true);
		this.view.setMaximizable(true);
		this.view.setResizable(true);
		this.view.moveToFront();
		this.view.requestFocus();

	}

	private void initConstruirTabela() {
		tabelaBonus = new DefaultTableModel(new Object[][][][] {},
				new String[] { "Data do Caculo", "Cargo", "Tipo de Bonus", "Valor de Bonus" });

	}

	private void getHistoricoById(int id) throws Exception {
		carregarTabela(dao.getHistoricoBonusByIdFuncionario(id));
	}

	private void getSearchHistorico() throws Exception {
		HistoricoBonusFilterDTO dtoFilter = new HistoricoBonusFilterDTO();
		
		dtoFilter.setId(idFuncionario);
		dtoFilter.setNome(this.view.getTxtNome().getText());
		
		if (view.getSeletorDataBusca().getDate() != null) {
			LocalDate dataEscolhida = DateUtils.asLocalDate(view.getSeletorDataBusca().getDate());
			dtoFilter.setDate(dataEscolhida);
		}

		carregarTabela(dao.getSerachHistoricoBonus(dtoFilter));
	}

	private void carregarTabela(Collection c) {
		tabelaBonus.setNumRows(0);
		DecimalFormat df = new DecimalFormat("#.##");

		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			HistoricoBonusDTO bonus = (HistoricoBonusDTO) it.next();
			tabelaBonus
					.addRow(new Object[] { bonus.getDataCalculo().format((DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
							bonus.getCargoFuncionario(), bonus.getTipoBonus(), df.format(bonus.getValorBonus()) });
		}

		view.getTblHistorico().setModel(tabelaBonus);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		view.getTblHistorico().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		view.getTblHistorico().getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		view.getTblHistorico().getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	}
	
	public void limparFiltro() {
		view.getTxtNome().setText("");
		view.getSeletorDataBusca().setDate(null);
	}
	
	public HistoricoBonusView getView() {
		return view;
	}

	private void initActrionListener() {
		view.getBtnPesquisar().addActionListener((ActionEvent ae) -> {
			try {
				getSearchHistorico();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
		});

		view.getBtnFechar().addActionListener((ActionEvent ae) -> {
			view.dispose();
		});
	
		view.getBtnLimpar().addActionListener((ActionEvent ae) -> {
			limparFiltro();
		});
	}
}
