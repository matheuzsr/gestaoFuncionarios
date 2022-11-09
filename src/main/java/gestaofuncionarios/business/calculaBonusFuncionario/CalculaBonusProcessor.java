package gestaofuncionarios.business.calculaBonusFuncionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gestaofuncionarios.business.calculaBonusFuncionario.handlers.AssiduidadeBonus;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.BonusHandler;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.FuncionarioMes;
import gestaofuncionarios.business.calculaBonusFuncionario.handlers.TempoServicoHandler;
import gestaofuncionarios.model.Bonus;
import gestaofuncionarios.model.Funcionario;

public class CalculaBonusProcessor {
    List<Bonus> bonusAplicadosList = new ArrayList<>();

    public void run(Funcionario funcionario, LocalDate localDate) {
        List<BonusHandler> handlersList = new ArrayList<>(Arrays.asList(
                new AssiduidadeBonus(),
                new TempoServicoHandler(),
                new FuncionarioMes()));

        for (BonusHandler handler : handlersList) {
            try {
                handler.calcular(funcionario, localDate, this.bonusAplicadosList);

            } catch (Exception e) {
                // TODO: Criar array para armazenar os erros que foram levantados de dentro do
                // calcular
                // e levantar eles para cima como não checadas
                throw new RuntimeException(e.getStackTrace().toString());
            }
        }

        this.salvarBonusFuncionario(funcionario);

    }

    private void salvarBonusFuncionario(Funcionario funcionario) {
        // TODO: pegar bonusAplicadosList e salvar nas tabelas,
        // Repository (f, b)
        // historico e bonus
        // usar a repository com o join
    }

}
