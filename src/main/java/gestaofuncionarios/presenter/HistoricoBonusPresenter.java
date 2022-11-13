package gestaofuncionarios.presenter;

import gestaofuncionarios.dados.dao.HistoricoBonusDAO;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.view.HistoricoBonusView;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HistoricoBonusPresenter {

    private final HistoricoBonusView view;
    private HistoricoBonusDAO dao;
    private DefaultTableModel tabelaBonus;

    public HistoricoBonusPresenter(int id) throws Exception {
        this.view = new HistoricoBonusView();
        dao = new HistoricoBonusDAOSQLite();
        initConstruirTabela();

        if (id > 0) {
            getHistoricoById(id);
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
        tabelaBonus = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"Data do Caculo", "Cargo", "Tipo de Bonus", "Valor de Bonus"}
        );

    }

    private void getHistoricoById(int id) throws Exception {
        carregarTabela(dao.getHistoricoBonusByIdFuncionario(id));
    }

    private void getHistoricoByName() throws Exception {
        String name = this.view.getTxtNome().getText();
        carregarTabela(dao.getHistoricoBonusByNameFuncionario(name));    
    }

    private void carregarTabela(Collection c) {
        tabelaBonus.setNumRows(0);

        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            HistoricoBonusDTO bonus = (HistoricoBonusDTO) it.next();
            tabelaBonus.addRow(new Object[]{
                bonus.getDataCalculo(),
                bonus.getCargoFuncionario(),
                bonus.getTipoBonus(),
                bonus.getValorBonus()
            });
        }

        view.getTblHistorico().setModel(tabelaBonus);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        view.getTblHistorico().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }

    public HistoricoBonusView getView() {
        return view;
    }

    private void initActrionListener() {
        view.getBtnPesquisar().addActionListener((ActionEvent ae) -> {
            try {
                getHistoricoByName();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        });

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });
    }
}
