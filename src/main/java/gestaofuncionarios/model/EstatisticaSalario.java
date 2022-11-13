package gestaofuncionarios.model;

import java.time.LocalDate;

public class EstatisticaSalario {

    private final Double media;
    private final Double somatorio;
    private final Double desvioPadrao;
    private final Double maiorSalario;
    private final Double menorSalario;
    private final Double qtdSalariosMes;
    private final Double coeficienteVariacao;
    private final LocalDate mesEstatistica;

    public EstatisticaSalario(LocalDate mesEstatistica, Double media, Double somatorio, Double desvioPadrao,
            Double maiorSalario, Double menorSalario, Double qtdSalariosMes, Double coeficienteVariacao) {
        this.media = media;
        this.somatorio = somatorio;
        this.desvioPadrao = desvioPadrao;
        this.maiorSalario = maiorSalario;
        this.menorSalario = menorSalario;
        this.qtdSalariosMes = qtdSalariosMes;
        this.coeficienteVariacao = coeficienteVariacao;
        this.mesEstatistica = mesEstatistica;
    }

    public Double getSomatorio() {
        return somatorio;
    }

    public Double getDesvioPadrao() {
        return desvioPadrao;
    }

    public Double getMaiorSalario() {
        return maiorSalario;
    }

    public Double getMenorSalario() {
        return menorSalario;
    }

    public Double getQtdSalariosMes() {
        return qtdSalariosMes;
    }

    public Double getMedia() {
        return media;
    }

    public Double getCoeficienteVariacao() {
        return coeficienteVariacao;
    }

    public int getMes() {
        return mesEstatistica.getMonthValue();
    }

    public int getAno() {
        return mesEstatistica.getYear();
    }
}
