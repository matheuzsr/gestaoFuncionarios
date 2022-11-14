package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.observer.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

public final class FuncionarioDAOSQLite extends Observable implements FuncionarioDAO {
	private List<Funcionario> funcionarios = new ArrayList<>();

	private final SQLiteDB BD = new SQLiteDB();

	@Override
	public List<Funcionario> getAll() {
		List<Funcionario> funcionarios = new ArrayList<>();

		try {
			StringBuilder str = new StringBuilder();
			str.append("SELECT * FROM funcionario");
			str.append(" WHERE deleted_at is null");

			BD.conectar();
			BD.consultar(str.toString());

			while (BD.getRs().next()) {
				int id = BD.getRs().getInt("id");
				String nome = BD.getRs().getString("nome");
				LocalDate dataNascimento = LocalDate.parse(BD.getRs().getString("data_nascimento"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String cargo = BD.getRs().getString("cargo");
				double salarioBase = BD.getRs().getDouble("salario_base");

				Funcionario f = new Funcionario(id, nome, dataNascimento, cargo, salarioBase);

				f.setFaltas(BD.getRs().getInt("faltas"));
				f.setDistanciaDoTrabalho(BD.getRs().getInt("distancia_trabalho"));

				if (BD.getRs().getString("data_admissao") != "null") {
					f.setDataAdmissao(LocalDate.parse(BD.getRs().getString("data_admissao"),
							DateTimeFormatter.ofPattern("yyyy-MM-dd")));

				}
				funcionarios.add(f);
			}

			BD.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getStackTrace());
		}

		return funcionarios;
	}

	@Override
	public boolean add(Funcionario funcionario) throws Exception {
		boolean add = funcionarios.add(funcionario);
		if (add) {
			BD.conectar();

			String sql = "INSERT INTO funcionario"
					+"(nome, data_nascimento, cargo, salario_base, salario, faltas, distancia_trabalho, data_admissao)"
					+" VALUES (?,'" + funcionario.getNome() + "','"
					+ funcionario.getDataNascimento() + "','" + funcionario.getCargo() + "','"
					+ funcionario.getSalarioBase() + "','" + funcionario.getSalario() + "','" + funcionario.getFaltas()
					+ "','" + funcionario.getDistanciaDoTrabalho() + "','" + funcionario.getDataAdmissao() + "','" + "null" + "')";

			StringBuilder str = new StringBuilder();

			str.append("INSERT INTO");
			str.append(" funcionario(nome, data_nascimento, cargo, salario_base, faltas, distancia_trabalho, data_admissao)");
			str.append(" VALUES ('");
			str.append(funcionario.getNome()).append("'").append(",").append("'");
			str.append(funcionario.getDataNascimento()).append("'").append(",").append("'");
			str.append(funcionario.getCargo()).append("'").append(",").append("'");
			str.append(funcionario.getSalarioBase()).append("'").append(",").append("'");
			str.append(funcionario.getFaltas()).append("'").append(",").append("'");
			str.append(funcionario.getDistanciaDoTrabalho()).append("'").append(",").append("'");
			str.append(funcionario.getDataAdmissao()).append("'");
			str.append(")");

			BD.atualizar(str.toString());
			BD.close();
		}

		this.notificarObservers(this.getAll());
		return add;
	}

	@Override
	public List<Funcionario> getFuncionariosByName(String nome) throws Exception {
		List<Funcionario> listaFuncionarios = new ArrayList<>();
		StringBuilder str = new StringBuilder();
		str.append("SELECT * FROM funcionario WHERE nome LIKE '%");
		str.append(nome).append("%'");
		str.append(" and deleted_at is null");

		BD.conectar();
		BD.consultar(str.toString());

		while (BD.getRs().next()) {
			int id = BD.getRs().getInt("id");
			String nomeFuncionario = BD.getRs().getString("nome");

			LocalDate dataNascimento = LocalDate.parse(BD.getRs().getString("data_nascimento"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String cargo = BD.getRs().getString("cargo");

			double salarioBase = BD.getRs().getDouble("salario_base");

			LocalDate dataAdmissao = LocalDate.parse(BD.getRs().getString("data_admissao"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			Funcionario f = new Funcionario(id, nomeFuncionario, dataNascimento, cargo, salarioBase);
			f.setFaltas(BD.getRs().getInt("faltas"));
			f.setDistanciaDoTrabalho(BD.getRs().getInt("distancia_trabalho"));
			f.setDataAdmissao(dataAdmissao);

			listaFuncionarios.add(f);
		}
		BD.close();
		return listaFuncionarios;
	}

	@Override
	public Funcionario getById(int searchId) throws Exception {
		Funcionario f = null;

		StringBuilder str = new StringBuilder();
		str.append("SELECT * FROM funcionario WHERE id='");
		str.append(searchId).append("'");
		str.append(" and deleted_at is null");

		BD.conectar();
		BD.consultar(str.toString());

		while (BD.getRs().next()) {
			int id = BD.getRs().getInt("id");
			String nomeFuncionario = BD.getRs().getString("nome");

			LocalDate dataNascimento = LocalDate.parse(BD.getRs().getString("data_nascimento"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String cargo = BD.getRs().getString("cargo");

			double salarioBase = BD.getRs().getDouble("salario_base");

			LocalDate dataAdmissao = LocalDate.parse(BD.getRs().getString("data_admissao"),
					DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			f = new Funcionario(id, nomeFuncionario, dataNascimento, cargo, salarioBase);
			f.setFaltas(BD.getRs().getInt("faltas"));
			f.setDistanciaDoTrabalho(BD.getRs().getInt("distancia_trabalho"));
			f.setDataAdmissao(dataAdmissao);
		}
		BD.close();
		return f;
	}

	@Override
	public void update(Funcionario funcionario) throws Exception {
		StringBuilder str = new StringBuilder();

		BD.conectar();

		str.append(" UPDATE funcionario ");
		str.append(" set ");
		str.append(" nome = ").append("'").append(funcionario.getNome()).append("'").append(",");
		str.append(" data_nascimento  = ").append("'").append(funcionario.getDataNascimento()).append("'").append(",");
		str.append(" cargo  = ").append("'").append(funcionario.getCargo()).append("'").append(",");
		str.append(" salario_base = ").append(funcionario.getSalarioBase()).append(",");
		str.append(" data_admissao  = ").append("'").append(funcionario.getDataAdmissao()).append("'").append(",");
		str.append(" faltas  = ").append(funcionario.getFaltas()).append(",");
		str.append("distancia_trabalho = ").append(funcionario.getDistanciaDoTrabalho());
		str.append(" Where id =").append(funcionario.getIdFuncionario());

		BD.atualizar(str.toString());

		BD.close();

		this.notificarObservers(this.getAll());
	}

	@Override
	public boolean delete(int idFuncionario) throws Exception {

		StringBuilder str = new StringBuilder();

		BD.conectar();

		str.append(" UPDATE funcionario ");
		str.append(" set ");
		str.append(" deleted_at = ").append("'").append(LocalDate.now()).append("'");
		str.append(" Where id =").append(idFuncionario);

		BD.atualizar(str.toString());

		BD.close();

		this.notificarObservers(this.getAll());

		return true;
	}

	@Override
	public boolean contains(Funcionario funcionario) {
		return this.funcionarios.contains(funcionario);
	}

	@Override
	protected void notificarObservers(List<Funcionario> funcionarios) {

		observerList.forEach(observer -> {
			observer.update(funcionarios);
		});

	}

}
