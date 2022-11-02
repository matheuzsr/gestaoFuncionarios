package gestaofuncionarios.model;

import java.util.ArrayList;

public class Funcionario implements Comparable<Funcionario> {

    private int idFuncionario;
    private String nome;
    private String cargo;
    private int faltas = -1;
    private double salario;
    private double salarioBase;
    private double distanciaDoTrabalho = -1;
    private double totalBonus = 0;
    private ArrayList<Bonus> bonusRecebidos = new ArrayList<>();

    public Funcionario(String nome, String cargo, double salarioBase) throws Exception {
        this.nome = nome;
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
