package entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa {
	private int id;
	private String cargo;
	private LocalDate dtContratacao;
	private float salario;
	private String cpf;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public LocalDate getDtContratacao() {
		return dtContratacao;
	}

	public void setDtContratacao(LocalDate dtContratacao) {
		this.dtContratacao = dtContratacao;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
