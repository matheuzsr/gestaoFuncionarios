package gestaofuncionarios.presenter;

import gestaofuncionarios.business.CalculaSalarioFuncionario;
import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricioSalarioDAOSQLite;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.dto.HistoricoCalcularSalarioDTO;
import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.model.HistoricoBonus;
import gestaofuncionarios.utils.DateUtils;
import gestaofuncionarios.view.CalculadoraSalarioView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CalculadoraSalarioPresenter {

    private CalculadoraSalarioView view;
    private List<HistoricoBonus> historicoBonusList = new ArrayList<>();
    private CalculaSalarioFuncionario calcularSalrioFuncionario;
    private FuncionarioDAOSQLite funcionarioDAO;
    private DefaultTableModel tabelaCalculoSalario;
    private HistoricioSalarioDAOSQLite historicioSalarioDAOSQLite;

    public CalculadoraSalarioPresenter() {
        this.view = new CalculadoraSalarioView();
        this.calcularSalrioFuncionario = new CalculaSalarioFuncionario();
        funcionarioDAO = new FuncionarioDAOSQLite();
        historicioSalarioDAOSQLite = new HistoricioSalarioDAOSQLite();
        initConstruirTabela();
        initActrionListener();
    }

    public CalculadoraSalarioView getView() {
        return this.view;
    }

    public void CalcularSalario() {
        if (view.getSeletorDataCalculo().getDate() != null) {
            LocalDate dataEscolhida = DateUtils.asLocalDate(view.getSeletorDataCalculo().getDate());
            List list = new ArrayList(funcionarioDAO.getFuncionarios());
            try {
                this.calcularSalrioFuncionario.calcularSalario(list, dataEscolhida, false);
                carregarTabela(historicioSalarioDAOSQLite.getAllHistoricoBonus());
            } catch (Exception ex) {
                Logger.getLogger(CalculadoraSalarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void buscar() {

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

        });
    }

    private void initConstruirTabela() {
        tabelaCalculoSalario = new DefaultTableModel(new Object[][][][]{},
                new String[]{"Funcionario", "Data", "Salário base (R$)", "Bônus (R$)", "Salário (R$)"});
    }

    private void carregarTabela(Collection c) {
        tabelaCalculoSalario.setNumRows(0);

        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            HistoricoCalcularSalarioDTO HistoricoCalcularSalario = (HistoricoCalcularSalarioDTO) it.next();
            tabelaCalculoSalario.addRow(new Object[]{HistoricoCalcularSalario.getNomeFuncionario(),
                HistoricoCalcularSalario.getDataCalculo(), HistoricoCalcularSalario.getValorSalarioBase(),
                HistoricoCalcularSalario.getValorBonus(), HistoricoCalcularSalario.getValorSalrio()});
        }

        view.getTabelaFuncionarios().setModel(tabelaCalculoSalario);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        view.getTabelaFuncionarios().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }

}
