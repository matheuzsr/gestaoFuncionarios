package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.model.HistoricoBonus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class HistoricoBonusDAOSQLite implements HistoricoBonusDAO {

    private final SQLiteDB BD = new SQLiteDB();


    @Override
    public Collection<HistoricoBonus> getAllHistoricoBonus() throws Exception {
       ArrayList<HistoricoBonus> listHistoricoBonus =  new ArrayList<HistoricoBonus>();
       StringBuilder str = new StringBuilder();
       
       str.append(" SELECT * ");
       str.append(" FROM historico_bonus hb ");
       BD.conectar();
       BD.consultar(str.toString());
    
       while (BD.getRs().next()) {
          int id = BD.getRs().getInt("id");  
          int idFuncionario = BD.getRs().getInt("id_funcionario");  
          LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          int idBonus = BD.getRs().getInt("id_bonus");  
          HistoricoBonus historicoBonus = new HistoricoBonus(idFuncionario,idBonus, data);
          historicoBonus.setIdHistoricoBonus(id);
          listHistoricoBonus.add(historicoBonus);
       }
       
       BD.close();
       
       return listHistoricoBonus;
    }
    
}
