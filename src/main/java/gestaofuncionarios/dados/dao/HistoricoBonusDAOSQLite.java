package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.model.HistoricoBonus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class HistoricoBonusDAOSQLite implements HistoricoBonusDAO {

    private final SQLiteDB BD = new SQLiteDB();

    @Override
    public Collection<HistoricoBonusDTO> getHistoricoBonusByIdFuncionario(int idFuncionario) throws Exception {
       ArrayList<HistoricoBonusDTO> listHistoricoBonus =  new ArrayList<>();
       StringBuilder str = new StringBuilder();
       
       str.append(" SELECT  ");
       str.append(" hb.data_inclusao as data_inclusao, ");
       str.append(" b.tipo as tipo , ");
       str.append(" hb.valor as valor , ");
       str.append(" f.cargo as cargo  ");
       str.append(" FROM historico_bonus hb ");
       str.append( " join bonus b on b.id = hb.id_bonus ");
       str.append( " join funcionario f on f.id = hb.id_funcionario ");
       str.append(" WHERE hb.id_funcionario = ").append(idFuncionario);
       BD.conectar();
       BD.consultar(str.toString());
    
       while (BD.getRs().next()) {
          Double valor = BD.getRs().getDouble("valor");  
          String cargo = BD.getRs().getString("cargo");  
          LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          String tipo = BD.getRs().getString("tipo");  
          HistoricoBonusDTO historicoBonus = new HistoricoBonusDTO(tipo,valor,data,cargo);
          listHistoricoBonus.add(historicoBonus);
       }
       
       BD.close();
       
       return listHistoricoBonus;
    }

    @Override
    public Collection<HistoricoBonusDTO> getHistoricoBonusByNameFuncionario(String NameFuncionario) throws Exception {
       ArrayList<HistoricoBonusDTO> listHistoricoBonus =  new ArrayList<>();
       StringBuilder str = new StringBuilder();
       
       str.append(" SELECT  ");
       str.append(" hb.data_inclusao as data_inclusao, ");
       str.append(" b.tipo as tipo , ");
       str.append(" hb.valor as valor , ");
       str.append(" f.cargo as cargo  ");
       str.append(" FROM historico_bonus hb ");
       str.append(" join bonus b on b.id = hb.id_bonus ");
       str.append(" join funcionario f on f.id = hb.id_funcionario ");
       str.append(" WHERE b.tipo like ").append("'").
               append(NameFuncionario).append("'");
       BD.conectar();
       BD.consultar(str.toString()); 
    
       while (BD.getRs().next()) {
          Double valor = BD.getRs().getDouble("valor");  
          String cargo = BD.getRs().getString("cargo");  
          LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          String tipo = BD.getRs().getString("tipo");  
          HistoricoBonusDTO historicoBonus = new HistoricoBonusDTO(tipo,valor,data,cargo);
          listHistoricoBonus.add(historicoBonus);
       }
       
       BD.close();
       
       return listHistoricoBonus;
    }
    
    @Override
    public void add(HistoricoBonus historicoBonus) throws Exception{
        StringBuilder str = new StringBuilder();
   
            BD.conectar();

            str.append(" INSERT INTO historico_bonus (  " );
            str.append(" data_inclusao, id_funcionario, id_bonus, valor ").append(" ) ");
            str.append(" Values ( ");
            str.append("'").append(historicoBonus.getDataInclusao()).append("'").append(",");
            str.append(historicoBonus.getIdFuncionario()).append(",");
            str.append(historicoBonus.getIdBonus()).append(",");
            str.append(historicoBonus.getValorBonusRecebido()).append(")");
            
            BD.atualizar(str.toString());
            BD.close();
        }
    
}
