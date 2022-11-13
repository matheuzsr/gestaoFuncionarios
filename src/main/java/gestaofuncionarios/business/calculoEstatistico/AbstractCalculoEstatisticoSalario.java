package gestaofuncionarios.business.calculoEstatistico;

import gestaofuncionarios.dados.dao.EstatisticaSalarioDAO;
import gestaofuncionarios.dados.dao.EstatisticaSalarioDAOSQLite;
import gestaofuncionarios.dto.HistoricoSalarioDTO;
import gestaofuncionarios.model.EstatisticaSalario;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCalculoEstatisticoSalario {
    EstatisticaSalarioDAO dao;

    public AbstractCalculoEstatisticoSalario(EstatisticaSalarioDAO dao) {
        this.dao = dao;
    }

    public abstract List<EstatisticaSalario> calcular() throws Exception;
}
