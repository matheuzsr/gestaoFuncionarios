package gestaofuncionarios.dados.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.dto.HistoricoSalarioDTO;
import gestaofuncionarios.model.EstatisticaSalario;

public class EstatisticaSalarioDAOSQLite implements EstatisticaSalarioDAO {
  private final SQLiteDB BD = new SQLiteDB();

  @Override
  public List<HistoricoSalarioDTO> getAllMeses(String operacao) throws Exception {
    List<HistoricoSalarioDTO> estatisticaHistoricoSalariosMeses =  new ArrayList<>();

       StringBuilder str = new StringBuilder();

       str.append("SELECT strftime('%m', historicoSalarioAno.data_inclusao) as mes,");
       str.append(operacao);
       str.append("(valor_salario) as calculo, ");
       str.append("        historicoSalarioAno.*");
       str.append("    from (select  strftime('%Y', historico_salario.data_inclusao) as ano, historico_salario.*");
       str.append("            from historico_salario");
       str.append("            order by ano");
       str.append("    ) as historicoSalarioAno");
       str.append("    group by mes, ano;");

       BD.conectar();
       BD.consultar(str.toString());

       while (BD.getRs().next()) {
          int mes = BD.getRs().getInt("mes");
          int ano = BD.getRs().getInt("ano");
          double calculoOperacao = BD.getRs().getDouble("calculo");
          LocalDate dataInclusao = LocalDate.parse(BD.getRs().getString("data_inclusao"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

           estatisticaHistoricoSalariosMeses.add(new HistoricoSalarioDTO(calculoOperacao, dataInclusao, mes, ano));

       }

       BD.close();

       if (estatisticaHistoricoSalariosMeses.isEmpty()) {
          throw new Error("Nenhum dado disponível para calculo da estatística");
       }

       return estatisticaHistoricoSalariosMeses;
  }
}