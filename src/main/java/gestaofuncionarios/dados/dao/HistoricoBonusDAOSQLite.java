package gestaofuncionarios.dados.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.dto.HistoricoBonusDTO;
import gestaofuncionarios.dto.HistoricoBonusFilterDTO;
import gestaofuncionarios.model.HistoricoBonus;

public class HistoricoBonusDAOSQLite implements HistoricoBonusDAO {

	private final SQLiteDB BD = new SQLiteDB();

	@Override
	public List<HistoricoBonusDTO> getSerachHistoricoBonus(HistoricoBonusFilterDTO historicoBonusFilterDTO)
			throws Exception {
		ArrayList<HistoricoBonusDTO> listHistoricoBonus = new ArrayList<>();
		StringBuilder str = new StringBuilder();

		str.append(" SELECT distinct  ");
		str.append(" hb.data_inclusao as data_inclusao, ");
		str.append(" b.tipo as tipo , ");
		str.append(" hb.valor as valor , ");
		str.append(" f.cargo as cargo  ");
		str.append(" FROM historico_bonus hb ");
		str.append(" join bonus b on b.id = hb.id_bonus ");
		str.append(" join funcionario f on f.id = hb.id_funcionario ");
		str.append(" WHERE 1=1 ");
		str.append(" and f.id = ").append(historicoBonusFilterDTO.getId());

		if (!historicoBonusFilterDTO.getNome().isEmpty()) {
			str.append(" and b.tipo like ").append("'").append(historicoBonusFilterDTO.getNome()).append("'");
		}

		if (historicoBonusFilterDTO.getDate() != null) {
			str.append(" and hb.data_inclusao = ").append("'").append(historicoBonusFilterDTO.getDate()).append("'");
		}

		BD.conectar();
		BD.consultar(str.toString());

		while (BD.getRs().next()) {
			Double valor = BD.getRs().getDouble("valor");
			String cargo = BD.getRs().getString("cargo");
			LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String tipo = BD.getRs().getString("tipo");
			HistoricoBonusDTO historicoBonus = new HistoricoBonusDTO(tipo, valor, data, cargo);
			listHistoricoBonus.add(historicoBonus);
		}

		BD.close();

		return listHistoricoBonus;
	}

	@Override
	public List<HistoricoBonusDTO> getHistoricoBonusByIdFuncionario(int idFuncionario) throws Exception {
		ArrayList<HistoricoBonusDTO> listHistoricoBonus = new ArrayList<>();
		StringBuilder str = new StringBuilder();

		str.append(" SELECT  ");
		str.append(" hb.data_inclusao as data_inclusao, ");
		str.append(" b.tipo as tipo , ");
		str.append(" hb.valor as valor , ");
		str.append(" f.cargo as cargo  ");
		str.append(" FROM historico_bonus hb ");
		str.append(" join bonus b on b.id = hb.id_bonus ");
		str.append(" join funcionario f on f.id = hb.id_funcionario ");
		str.append("Where f.id = ").append(idFuncionario);

		BD.conectar();
		BD.consultar(str.toString());

		while (BD.getRs().next()) {
			Double valor = BD.getRs().getDouble("valor");
			String cargo = BD.getRs().getString("cargo");
			LocalDate data = LocalDate.parse(BD.getRs().getString("data_inclusao"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String tipo = BD.getRs().getString("tipo");
			HistoricoBonusDTO historicoBonus = new HistoricoBonusDTO(tipo, valor, data, cargo);
			listHistoricoBonus.add(historicoBonus);
		}

		BD.close();

		return listHistoricoBonus;
	}

	@Override
	public void add(HistoricoBonus historicoBonus) throws Exception {
		StringBuilder str = new StringBuilder();

		BD.conectar();

		str.append(" INSERT INTO historico_bonus (  ");
		str.append(" data_inclusao, id_funcionario, id_bonus, valor ").append(" ) ");
		str.append(" Values ( ");
		str.append("'").append(historicoBonus.getDataInclusao()).append("'").append(",");
		str.append(historicoBonus.getIdFuncionario()).append(",");
		str.append(historicoBonus.getIdBonus()).append(",");
		str.append(historicoBonus.getValorBonusRecebido()).append(")");

		BD.atualizar(str.toString());
		BD.close();
	}

	@Override
	public HistoricoBonus getHistoricoBonusByData(LocalDate data, int idBonus, int idFuncionario) throws Exception {
		HistoricoBonus historicoBonus = null;
		StringBuilder str = new StringBuilder();

		str.append(" SELECT distinct  ");
		str.append(" hb.id as id, ");
		str.append(" hb.data_inclusao as data_inclusao, ");
		str.append(" hb.valor as valor, ");
		str.append(" hb.id_funcionario as id_funcionario, ");
		str.append(" hb.id_bonus as id_bonus");
		str.append(" FROM historico_bonus hb ");
		str.append(" WHERE 1=1 ");
		str.append(" and hb.data_inclusao = ").append("'").append(data).append("'");
		str.append(" and hb.id_bonus = ").append(idBonus);
		str.append(" and hb.id_funcionario = " ).append(idFuncionario);	
		
		BD.conectar();
		BD.consultar(str.toString());

		while (BD.getRs().next()) {
			Integer idHistoricoBonus = BD.getRs().getInt("id");
			Integer idBonusRecebido = BD.getRs().getInt("id_bonus");
			Integer idFuncionarioRecebido = BD.getRs().getInt("id_funcionario");
			Double valor = BD.getRs().getDouble("valor");
			LocalDate data_inclusao = LocalDate.parse(BD.getRs().getString("data_inclusao"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			if (idHistoricoBonus != null) {
				historicoBonus = new HistoricoBonus(idFuncionarioRecebido, idBonusRecebido, data_inclusao, valor);
				historicoBonus.setIdHistoricoBonus(idHistoricoBonus);
			}
		}

		BD.close();

		return historicoBonus;
	}

	@Override
	public void update(HistoricoBonus historicoBonus) throws Exception {

		StringBuilder str = new StringBuilder();

		BD.conectar();

		str.append(" UPDATE historico_bonus ");
		str.append(" set ");
		str.append(" valor = ").append(historicoBonus.getValorBonusRecebido());
		str.append(" where 1=1 ");
		str.append(" and id = ").append(historicoBonus.getIdHistoricoBonus());
		str.append(" and id_bonus = ").append(historicoBonus.getIdBonus());
	

		BD.atualizar(str.toString());
		BD.close();
	}

}
