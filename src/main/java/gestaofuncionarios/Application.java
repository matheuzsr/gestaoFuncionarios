/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package gestaofuncionarios;

import gestaofuncionarios.dados.dao.FuncionarioDAO;
import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.model.Funcionario;
import gestaofuncionarios.presenter.GestaoFuncionariosPresenter;

/**
 *
 * @author matheus-ufes
 */
public class Application {

    public static void main(String[] args) {
        try {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOSQLite();
        GestaoFuncionariosPresenter presenter = new GestaoFuncionariosPresenter(funcionarioDAO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
