package gestaofuncionarios.dados.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.dto.HistoricoCalcularSalarioDTO;
import gestaofuncionarios.model.HistoricoSalario;

public class HistoricoSalarioDAOSQLite implements HistoricoSalarioDAO {

    private final SQLiteDB BD = new SQLiteDB();

    @Override
    public List<HistoricoCalcularSalarioDTO> getAllHistoricoSalario() throws Exception {
        ArrayList<HistoricoCalcularSalarioDTO> listHistoricoBonus = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        str.append(" SELECT  ");
        str.append(" hs.data_inclusao as data_inclusao, ");
        str.append(" hs.valor_salario as valor_salario , ");
        str.append(" hs.valor_bonus as valor_bonus , ");
        str.append(" f.salario_base as salario_base,  ");
        str.append(" f.nome as nome ");
        str.append(" FROM historico_salario hs ");
        str.append(" join funcionario f on f.id = hs.id_funcionario ");
        BD.conectar();
        BD.consultar(str.toString());

        while (BD.getRs().next()) {
            Double valorBonus = BD.getRs().getDouble("valor_bonus");
            Double valorSalario = BD.getRs().getDouble("valor_salario");
            Double valorSalarioBase = BD.getRs().getDouble("salario_base");
            String nome = BD.getRs().getString("nome");
            LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            HistoricoCalcularSalarioDTO dto = new HistoricoCalcularSalarioDTO(nome, data, valorBonus, valorSalarioBase, valorSalario);
            listHistoricoBonus.add(dto);
        }

        BD.close();

        return listHistoricoBonus;
    }
    
    @Override
    public List<HistoricoCalcularSalarioDTO> getHistoricoSalarioByData(LocalDate date) throws Exception {
        ArrayList<HistoricoCalcularSalarioDTO> listHistoricoBonus = new ArrayList<>();
        StringBuilder str = new StringBuilder();

        str.append(" SELECT  ");
        str.append(" hs.data_inclusao as data_inclusao, ");
        str.append(" hs.valor_salario as valor_salario , ");
        str.append(" hs.valor_bonus as valor_bonus , ");
        str.append(" f.salario_base as salario_base,  ");
        str.append(" f.nome as nome ");
        str.append(" FROM historico_salario hs ");
        str.append(" join funcionario f on f.id = hs.id_funcionario ");
        str.append(" where hs.data_inclusao = ").append("'").append(date).append("'");
        BD.conectar();
        BD.consultar(str.toString());

        while (BD.getRs().next()) {
            Double valorBonus = BD.getRs().getDouble("valor_bonus");
            Double valorSalario = BD.getRs().getDouble("valor_salario");
            Double valorSalarioBase = BD.getRs().getDouble("salario_base");
            String nome = BD.getRs().getString("nome");
            LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            HistoricoCalcularSalarioDTO dto = new HistoricoCalcularSalarioDTO(nome, data, valorBonus, valorSalarioBase, valorSalario);
            listHistoricoBonus.add(dto);
        }

        BD.close();

        return listHistoricoBonus;
    }
    
    @Override
    public void add(HistoricoSalario historicoSalario) throws Exception {
        StringBuilder str = new StringBuilder();

        BD.conectar();

        str.append(" INSERT INTO historico_salario (  ");
        str.append(" data_inclusao, valor_salario, valor_bonus, id_funcionario ").append(" ) ");
        str.append(" Values ( ");
        str.append("'").append(historicoSalario.getDataInclusao()).append("'").append(",");
        str.append(historicoSalario.getValorTotalSalario()).append(",");
        str.append(historicoSalario.getValorTotalBonus()).append(",");
        str.append(historicoSalario.getIdFuncionario()).append(")");

        BD.atualizar(str.toString());
        BD.close();

    }


}
