package gestaofuncionarios.presenter;

import gestaofuncionarios.dados.dao.HistoricoBonusDAO;
import gestaofuncionarios.dados.dao.HistoricoBonusDAOSQLite;
import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.view.HistoricoBonusView;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HistoricoBonusPresenter {
    
    private final HistoricoBonusView view;
    private HistoricoBonusDAO dao;
    private DefaultTableModel tabelaBonus;


    public HistoricoBonusPresenter(int id) throws Exception {
        this.view = new HistoricoBonusView();
        dao = new HistoricoBonusDAOSQLite();
        initConstruirTabela(); 
    
        if(id > 0){
            getHistoricoById(id);
        }
       
        view.getBtnPesquisar().addActionListener((ActionEvent ae) -> {
            try {
              this.getHistoricoByName();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        });
        
        this.view.setIconifiable(true);
        this.view.setIcon(true);
        this.view.setVisible(true);
        this.view.setMaximizable(true);
        this.view.setResizable(true);
        this.view.moveToFront();
        this.view.requestFocus();
         
    }
    
    private void initConstruirTabela(){
          tabelaBonus = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"Data do Caculo", "Cargo", "Tipo de Bonus","Valor de Bonus"}
        );

        tabelaBonus.setNumRows(0);
        view.getTblHistorico().setModel(tabelaBonus);
    }
    
   
    
    private void getHistoricoById(int id) throws Exception{
        for(HistoricoBonusDTO bonus: dao.getHistoricoBonusByIdFuncionario(id)){
            tabelaBonus.addRow(new Object[]{
                bonus.getDataCalculo(),
                bonus.getCargoFuncionario(),
                bonus.getTipoBonus(),
                bonus.getValorBonus()
            });
        }
    }
    
    private void getHistoricoByName() throws Exception{
        String name = this.view.getTxtNome().getName();
          for(HistoricoBonusDTO bonus: dao.getHistoricoBonusByNameFuncionario(name)){
              tabelaBonus.addRow(new Object[]{
                bonus.getDataCalculo(),
                bonus.getCargoFuncionario(),
                bonus.getTipoBonus(),
                bonus.getValorBonus()
            });
        }
    }
 
    public HistoricoBonusView getView(){
        return view;
    }
    
}
