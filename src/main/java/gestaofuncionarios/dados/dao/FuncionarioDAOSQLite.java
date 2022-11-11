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
    public void carregaFuncionarios() {
        this.funcionarios = new ArrayList<>();

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
    }

    @Override
    public boolean add(Funcionario funcionario) throws Exception {
        boolean add = funcionarios.add(funcionario);
        if (add) {
            BD.conectar();

            String sql = "INSERT INTO funcionario VALUES (?,'"
                    + funcionario.getNome() + "','"
                    + funcionario.getDataNascimento() + "','"
                    + funcionario.getCargo() + "','"
                    + funcionario.getSalarioBase() + "','"
                    + funcionario.getSalario() + "','"
                    + funcionario.getFaltas() + "','"
                    + funcionario.getDistanciaDoTrabalho() + "','"
                    + funcionario.getDataAdmissao() + "')";

            BD.atualizar(sql);
            BD.close();
        }

        this.notificarObservers();
        return add;
    }

    @Override
    public boolean remove(String nome) throws Exception {
        List<Funcionario> temp = new ArrayList<>();

        Iterator it = funcionarios.iterator();
        while (it.hasNext()) {
            Funcionario f = (Funcionario) it.next();
            if (!f.getNome().equals(nome)) {
                temp.add(f);
            }
        }

        BD.conectar();

        String sql = "DELETE FROM funcionario WHERE nome='" + nome + "'";
        BD.atualizar(sql);
        BD.close();
        funcionarios = temp;

        this.notificarObservers();

        return true;
    }

    @Override
    public Funcionario getFuncionarioByName(String nome) throws Exception {
        Funcionario f = null;
        BD.conectar();
        BD.consultar("SELECT * FROM funcionario WHERE nome='" + nome + "'");

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
            f.setFaltas(BD.getRs().getInt(6));
            f.setDistanciaDoTrabalho(BD.getRs().getInt(7));
            f.setDataAdmissao(dataAdmissao);
        }
        BD.close();
        return f;
    }

    @Override
    public boolean contains(Funcionario funcionario) {
        return this.funcionarios.contains(funcionario);
    }

    @Override
    public Collection<Funcionario> getFuncionarios() {
        carregaFuncionarios();

        return funcionarios;
    }

    @Override
    protected void notificarObservers() {
        carregaFuncionarios();

        observerList.forEach(observer -> {
            observer.update(this.funcionarios);
        });

    }
}
