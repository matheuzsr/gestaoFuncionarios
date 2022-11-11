package gestaofuncionarios.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import gestaofuncionarios.dados.dao.FuncionarioDAO;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.observer.Observer;
import gestaofuncionarios.view.BuscarFuncionarioView;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuscarFuncionarioPresenter implements Observer {

    private final FuncionarioDAO dao;
    private BuscarFuncionarioView view;
    private DefaultTableModel tabela;
    private List<Funcionario> funcionarioList;

    private final DecimalFormat format = new DecimalFormat("0.00");

    public BuscarFuncionarioPresenter(FuncionarioDAO dao) {
        view = new BuscarFuncionarioView();
        view.setTitle("Buscar Funcionario");
        this.dao = dao;

        criarTabela();
        carregarTabela(dao.getFuncionarios());

        view.getTblAtributos().setSelectionMode(0);

        view.getBtnBuscar().addActionListener((ActionEvent ae) -> {
            try {
                buscar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        });

        view.getBtnVisualizar().addActionListener((ActionEvent e) -> {
            visualizarFuncionario();
        });
        
        view.getBtnHistoryBonus().addActionListener((ActionEvent e) -> {
            try {
                verBonus();
            } catch (Exception ex) {
                Logger.getLogger(BuscarFuncionarioPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        view.getBtnFechar().addActionListener((ActionEvent e) -> {
            view.dispose();
        });

        view.getBtnVisualizar().setEnabled(false);
        view.getBtnHistoryBonus().setEnabled(false);
        view.getTblAtributos().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (view.getTblAtributos().getSelectedRow() > -1) {
                    handleEnableButtons();
                }
            }
        });

        view.setVisible(true);

    }

    private void buscar() throws Exception {
        String nome = view.getTxtValor().getText();
        Iterator<Funcionario> it = dao.getFuncionarios().iterator();

        TreeSet<Funcionario> treeSet = new TreeSet<>();

        while (it.hasNext()) {
            Funcionario funcionario = it.next();

            if (funcionario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                treeSet.add(funcionario);
            }
        }
        carregarTabela(treeSet);

    }

    private void visualizarFuncionario() {
        int row = view.getTblAtributos().getSelectedRow();
        String nome = (String) view.getTblAtributos().getValueAt(row, 1);

        Funcionario funcionario;
        try {
            funcionario = this.dao.getFuncionarioByName(nome);
            FuncionarioPresenter presenter = new FuncionarioPresenter(this.dao,
                    funcionario);
            GestaoFuncionariosPresenter.showPanel(presenter.getView(), false, false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    private void criarTabela() {
        tabela = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "ID", "Nome", "Idade", "Função", "Salario base"
                }) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

    }

    private void carregarTabela(Collection c) {
        tabela.setNumRows(0);

        Iterator<?> it = c.iterator();
        while (it.hasNext()) {
            Funcionario funcionario = (Funcionario) it.next();
            tabela.addRow(new Object[] {
                    funcionario.getIdFuncionario(),
                    funcionario.getNome(),
                    funcionario.getIdade(),
                    funcionario.getCargo(),
                    format.format(funcionario.getSalarioBase())
            });
        }

        view.getTblAtributos().setModel(tabela);
        view.getTblAtributos().getColumnModel().getColumn(0).setMaxWidth(40);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        view.getTblAtributos().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }
    
    private void verBonus() throws Exception{
        int row = view.getTblAtributos().getSelectedRow();
        int id = (int) view.getTblAtributos().getValueAt(row, 0);
        HistoricoBonusPresenter hb = new HistoricoBonusPresenter(id); 
        GestaoFuncionariosPresenter.showPanel(hb.getView(), false, false);
      
    }
    
    // TODO: colocar eles no change do select row table
    private void handleEnableButtons() {
        view.getBtnVisualizar().setEnabled(true);
        view.getBtnHistoryBonus().setEnabled(true);
    }

    public BuscarFuncionarioView getView() {
        return view;
    }

    @Override
    public void update(List<Funcionario> funcionarioList) {
        limparTabelaFuncionarios();

        for (Funcionario funcionario : funcionarioList) {
            tabela.addRow(new Object[] {
                    funcionario.getIdFuncionario(),
                    funcionario.getNome(),
                    funcionario.getIdade(),
                    funcionario.getCargo(),
                    format.format(funcionario.getSalarioBase())
            });
        }
    }

    private void limparTabelaFuncionarios() {
        if (this.tabela.getRowCount() > 0) {
            for (int i = this.tabela.getRowCount() - 1; i > -1; i--) {
                this.tabela.removeRow(i);
            }
        }
    }
}
