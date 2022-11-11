/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package gestaofuncionarios;

import gestaofuncionarios.dados.dao.FuncionarioDAOSQLite;
import gestaofuncionarios.presenter.GestaoFuncionariosPresenter;

/**
 *
 * @author matheus-ufes
 */
public class Application {

    public static void main(String[] args) throws Exception {
        try {
            FuncionarioDAOSQLite funcionarioDAO = new FuncionarioDAOSQLite();
            GestaoFuncionariosPresenter presenter = new GestaoFuncionariosPresenter(funcionarioDAO);

            funcionarioDAO.addObserver(presenter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
