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

public class CalculoEstatisticoSalario extends AbstractCalculoEstatisticoSalario {
    public CalculoEstatisticoSalario(EstatisticaSalarioDAO dao) {
        
        super(dao);
    }

    public List<EstatisticaSalario> calcular() throws Exception {
        List<EstatisticaSalario> estatisticaSalariosList = new ArrayList<>();

        List<HistoricoSalarioDTO> media = new ArrayList<>();
        List<HistoricoSalarioDTO> somatorio = new ArrayList<>();
        List<HistoricoSalarioDTO> desvioPadrao = new ArrayList<>();
        List<HistoricoSalarioDTO> maiorSalario = new ArrayList<>();
        List<HistoricoSalarioDTO> menorSalario = new ArrayList<>();
        List<HistoricoSalarioDTO> qtdSalariosMes = new ArrayList<>();
        List<HistoricoSalarioDTO> coeficienteVariacao = new ArrayList<>();

        try {

            media.addAll(this.dao.getAllMeses("avg")); 
            somatorio.addAll(this.dao.getAllMeses("sum"));
            desvioPadrao.addAll(this.dao.getAllMeses("stdev"));
            maiorSalario.addAll(this.dao.getAllMeses("max"));
            menorSalario.addAll(this.dao.getAllMeses("min"));
            qtdSalariosMes.addAll(this.dao.getAllMeses("count"));
            coeficienteVariacao.addAll(this.dao.getAllMeses("variance"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
        }

        for (int i = 0; i < media.size(); i++) {
            EstatisticaSalario estatisticaMes = new EstatisticaSalario(
                    media.get(i).getDataInclusao(),
                    media.get(i).getValorEstatistica(),
                    somatorio.get(i).getValorEstatistica(),
                    desvioPadrao.get(i).getValorEstatistica(),
                    maiorSalario.get(i).getValorEstatistica(),
                    menorSalario.get(i).getValorEstatistica(),
                    qtdSalariosMes.get(i).getValorEstatistica(),
                    coeficienteVariacao.get(i).getValorEstatistica());

            estatisticaSalariosList.add(estatisticaMes);
        }

        return estatisticaSalariosList;
    }
}
