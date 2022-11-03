package gestaofuncionarios.dados.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.JOptionPane;

import gestaofuncionarios.dados.ConexaoSQLite.SQLiteDB;
import gestaofuncionarios.model.Funcionario;

public final class FuncionarioDAOSQLite implements FuncionarioDAO {

    private TreeSet<Funcionario> funcionarios = null;
    private final SQLiteDB BD = new SQLiteDB();

    public FuncionarioDAOSQLite() {
        if (this.funcionarios == null) {
            this.funcionarios = new TreeSet<>();
        }
        carregaFuncionarios(); //Não faz sentindo. 
    }

    @Override
    public void carregaFuncionarios() {
        try {
            BD.conectar();
            BD.consultar("SELECT * FROM funcionario");

            while (BD.getRs().next()) {
                int id = BD.getRs().getInt(1);
                String nome = BD.getRs().getString(2);
                String cargo = BD.getRs().getString(3);
                double salarioBase = BD.getRs().getDouble(4);

                Funcionario f = new Funcionario(nome, cargo, salarioBase);
                f.setIdFuncionario(id);
                f.setFaltas(BD.getRs().getInt(6));
                f.setDistanciaDoTrabalho(BD.getRs().getInt(7));

                funcionarios.add(f);
            }

            BD.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar funcionarios");
        }
    }

    @Override
    public boolean add(Funcionario funcionario) throws Exception {
        boolean add = funcionarios.add(funcionario);
        if (add) {
            BD.conectar();

            String sql = "INSERT INTO funcionario VALUES (?,'"
                    + funcionario.getNome() + "','"
                    + funcionario.getCargo() + "','"
                    + funcionario.getSalarioBase() + "','"
                    + funcionario.getSalario() + "','"
                    + funcionario.getFaltas() + "','"
                    + funcionario.getDistanciaDoTrabalho() + "')";

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

        String sql = "DELETE FROM funcionario WHERE Nome='" + nome + "'";
        BD.atualizar(sql);
        BD.close();
        funcionarios = temp;
        return true;
    }

    @Override
    public Funcionario getFuncionarioByName(String nome) throws Exception {
        Funcionario f = null;
        BD.conectar();
        BD.consultar("SELECT * FROM funcionario WHERE Nome='" + nome + "'");

        while (BD.getRs().next()) {
            int id = BD.getRs().getInt(1);
            String nomeFuncionario = BD.getRs().getString(2);
            String cargo = BD.getRs().getString(3);
            double salarioBase = BD.getRs().getDouble(4);

            f = new Funcionario(nomeFuncionario, cargo, salarioBase);
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
        return funcionarios;
    }
}
