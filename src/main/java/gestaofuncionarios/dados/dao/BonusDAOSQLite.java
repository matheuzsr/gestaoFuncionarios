package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.model.Bonus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BonusDAOSQLite implements BonusDAO {
    private final SQLiteDB BD = new SQLiteDB();
   
    
    @Override
    public Bonus getbyId(int id) throws Exception {
       Bonus bonus = null;
       StringBuilder str = new StringBuilder();
       
       str.append(" SELECT * ");
       str.append(" FROM bonus b ");
       str.append(" WHERE 1 = 1");
       str.append(" AND b.id = ").append(id);
       BD.conectar();
       BD.consultar(str.toString());
    
       while (BD.getRs().next()) {
          int idBonus = BD.getRs().getInt("id");  
          String tipo = BD.getRs().getString("tipo");  
          LocalDate data = LocalDate.parse(BD.getRs().getString("data"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          Double valor = BD.getRs().getDouble("valor");
          bonus = new Bonus(tipo,valor,data);
          bonus.setIdBonus(idBonus);
       }
       BD.close();
       
       return bonus;
    }

    @Override
    public Collection<Bonus> getAllBonus() throws Exception{
      List<Bonus> listBonus =  new ArrayList<Bonus>();
       StringBuilder str = new StringBuilder();
       
       str.append(" SELECT * ");
       str.append(" FROM bonus b ");
       BD.conectar();
       BD.consultar(str.toString());
    
       while (BD.getRs().next()) {
          int idBonus = BD.getRs().getInt("id");  
          String tipo = BD.getRs().getString("tipo");  
          LocalDate data = LocalDate.parse(BD.getRs().getString("data"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          Double valor = BD.getRs().getDouble("valor");
          Bonus bonus = new Bonus(tipo,valor,data);
          bonus.setIdBonus(idBonus);
          listBonus.add(bonus);
       }
       
       BD.close();
       
       return listBonus;
    }
    
}
