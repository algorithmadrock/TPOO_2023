package entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
	private int id;
	private LocalDate dtDevolucao;
	private Cliente cliente;
	private Livro livro;
	private LocalDate dtEmprestimo;
	private float valor;

	public float calcularValor(LocalDate dtDevolucao, LocalDate dtEmprestimo) {
		valor = 20 + dtEmprestimo.until(dtDevolucao, ChronoUnit.DAYS) * 2;
		return valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDtDevolucao() {
		return dtDevolucao;
	}

	public void setDtDevolucao(LocalDate dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public LocalDate getDtEmprestimo() {
		return dtEmprestimo;
	}

	public void setDtEmprestimo(LocalDate dtEmprestimo) {
		this.dtEmprestimo = dtEmprestimo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
