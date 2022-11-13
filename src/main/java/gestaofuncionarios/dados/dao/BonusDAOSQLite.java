package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;

public class BonusDAOSQLite implements BonusDAO {
    private final SQLiteDB BD = new SQLiteDB();
   
    
    @Override
    public int getIdByNome(String tipoBonus) throws Exception {
       StringBuilder str = new StringBuilder();
       
       str.append(" SELECT b.id as id ");
       str.append(" FROM bonus b ");
       str.append(" WHERE 1 = 1");
       str.append(" AND b.tipo like '").append(tipoBonus).append("'");
       BD.conectar();
       BD.consultar(str.toString());
       int idBonus = BD.getRs().getInt("id");  
       BD.close();
       
       return idBonus;

    }

 
    
}
