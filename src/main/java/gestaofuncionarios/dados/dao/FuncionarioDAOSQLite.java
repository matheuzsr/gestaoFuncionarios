package gestaofuncionarios.dados.dao;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.model.Funcionario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.JOptionPane;

public final class FuncionarioDAOSQLite implements FuncionarioDAO {

    private TreeSet<Funcionario> funcionarios = new TreeSet<>();
    private final SQLiteDB BD = new SQLiteDB();

    public FuncionarioDAOSQLite() {
        // if (this.funcionarios == null) {
        // this.funcionarios = new TreeSet<>();
        // }
        // carregaFuncionarios();
    }

    @Override
    public void carregaFuncionarios() {
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

                Funcionario f = new Funcionario(nome, dataNascimento, cargo, salarioBase);
                f.setIdFuncionario(id);
                f.setFaltas(BD.getRs().getInt(6));
                f.setDistanciaDoTrabalho(BD.getRs().getInt(7));

                if (BD.getRs().getString("data_admissao") != "null") {
                    f.setDataAdmissao(LocalDate.parse(BD.getRs().getString("data_nascimento"),
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
        return add;
    }

    @Override
    public boolean remove(String nome) throws Exception {
        Iterator it = funcionarios.iterator();
        TreeSet<Funcionario> temp = new TreeSet<>();
        while (it.hasNext()) {
            Funcionario f = (Funcionario) it.next();
            if (!f.getNome().equals(nome)) {
                temp.add(f);
            }
        }

        BD.conectar();

        String sql = "DELETE FROM funcionario WHERE mome='" + nome + "'";
        BD.atualizar(sql);
        BD.close();
        funcionarios = temp;
        return true;
    }

    @Override
    public Funcionario getFuncionarioByName(String nome) throws Exception {
        Funcionario f = null;
        BD.conectar();
        BD.consultar("SELECT * FROM funcionario WHERE nome='" + nome + "'");

        while (BD.getRs().next()) {
            int id = BD.getRs().getInt(1);
            String nomeFuncionario = BD.getRs().getString(2);
            LocalDate dataNascimento = LocalDate.parse(BD.getRs().getString("data_nascimento"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String cargo = BD.getRs().getString(4);
            double salarioBase = BD.getRs().getDouble(5);

            f = new Funcionario(nomeFuncionario, dataNascimento, cargo, salarioBase);
            f.setIdFuncionario(id);
            f.setFaltas(BD.getRs().getInt(6));
            f.setDistanciaDoTrabalho(BD.getRs().getInt(7));
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
}
