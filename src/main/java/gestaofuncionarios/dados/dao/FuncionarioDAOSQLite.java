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

			BD.conectar();
			BD.consultar("SELECT * FROM funcionario");

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

			String sql = "INSERT INTO funcionario VALUES (?,'" + funcionario.getNome() + "','"
					+ funcionario.getDataNascimento() + "','" + funcionario.getCargo() + "','"
					+ funcionario.getSalarioBase() + "','" + funcionario.getSalario() + "','" + funcionario.getFaltas()
					+ "','" + funcionario.getDistanciaDoTrabalho() + "','" + funcionario.getDataAdmissao() + "')";

			BD.atualizar(sql);
			BD.close();
		}

		this.notificarObservers(this.getAll());
		return add;
	}

	@Override
	public boolean remove(int idFuncionario) throws Exception {
		List<Funcionario> temp = new ArrayList<>();

		BD.conectar();

		String sql = "DELETE FROM funcionario WHERE id='" + idFuncionario + "'";
		BD.atualizar(sql);
		BD.close();
		funcionarios = temp;

		this.notificarObservers(this.getAll());

		return true;
	}

	@Override
	public List<Funcionario> getFuncionariosByName(String nome) throws Exception {
		List<Funcionario> listaFuncionarios = new ArrayList<>();
		BD.conectar();
		BD.consultar("SELECT * FROM funcionario WHERE nome LIKE '%" + nome + "%'");

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
		BD.conectar();
		BD.consultar("SELECT * FROM funcionario WHERE id='" + searchId + "'");

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
