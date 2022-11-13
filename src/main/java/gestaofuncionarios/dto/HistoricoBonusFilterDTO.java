package gestaofuncionarios.dto;

import java.time.LocalDate;

public class HistoricoBonusFilterDTO {
	private int id;
	private String nome;
	private LocalDate date;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
