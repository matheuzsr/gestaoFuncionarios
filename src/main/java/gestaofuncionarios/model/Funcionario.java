package gestaofuncionarios.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Funcionario implements Comparable<Funcionario> {

    private int idFuncionario;
    private String nome;
    private String cargo;
    private int faltas = -1;
    private double salario;
    private LocalDate dataNascimento;
    private double salarioBase;
    private double distanciaDoTrabalho = -1;
    private LocalDate dataAdmissao;
    private double totalBonus = 0;
    private boolean funcionarioMes;
    private ArrayList<Bonus> bonusRecebidos = new ArrayList<>();

    public Funcionario(String nome, LocalDate dataNascimento, String cargo, double salarioBase) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
    }

    public void addBonus(Bonus bonus) {
        if (bonus.getValor() != 0) {
            bonusRecebidos.add(bonus);
        }
    }

    public void calcularSalario() {
        this.salario = this.salarioBase + this.calculaTotalBonus();
    }

    private double calculaTotalBonus() {
        bonusRecebidos.forEach((Bonus bonus) -> {
            totalBonus += bonus.getValor();
        });
        return totalBonus;
    }

    @Override
    public int compareTo(Funcionario funcionario) {
        return nome.compareTo(funcionario.nome);
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataAdmissao() {
        return this.dataAdmissao;
    }

    public int getAnosTempoTrabalho() {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(this.dataAdmissao, dateNow);
        return period.getYears();
    }

    public int getIdade() {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(this.dataNascimento, dateNow);
        return period.getYears();
    }

    public String getCargo() {
        return cargo;
    }

    public double getDistanciaDoTrabalho() {
        return distanciaDoTrabalho;
    }

    public void setDistanciaDoTrabalho(double distanciaDoTrabalho) {
        this.distanciaDoTrabalho = distanciaDoTrabalho;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public ArrayList<Bonus> getBonusRecebidos() {
        return bonusRecebidos;
    }

    public double getTotalBonus() {
        return this.calculaTotalBonus();
    }

    public void setFuncionarioMes(boolean isFuncionarioMes) {
        this.funcionarioMes = isFuncionarioMes;
    }

    public boolean isFuncionarioMes() {
        return funcionarioMes;
    }

    @Override
    public String toString() {
        return this.getIdFuncionario() + "#"
                + this.getNome() + "#"
                + this.getCargo() + "#"
                + this.salarioBase + "#"
                + this.faltas + "#"
                + this.distanciaDoTrabalho
                + this.bonusRecebidos.toString().replace("[", "")
                        .replace("]", "").replace(" ", "").replace(",", "");
    }
}
