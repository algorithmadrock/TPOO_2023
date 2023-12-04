package entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class Emprestimo {
	private int id;
	private LocalDate emprestimo, devolucao;
	private int idClie;
	private String nmClie;
	private Set<Livro> livros;
	private float valor;

	public Emprestimo() {
	
	}
	
	public float calcValor(int dias) {
		valor = (float)(dias * 1.25);
		return valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(LocalDate dtEmprestimo, int dias) {
		this.devolucao = emprestimo.plusDays(dias);
	}

	public String getNmClie() {
		return nmClie;
	}

	public void setNmClie(String nmClie) {
		this.nmClie = nmClie;
	}
	
	public int getIdClie() {
		return idClie;
	}

	public void setIdClie(int idClie) {
		this.idClie = idClie;
	}
	

	public Set<Livro> getLivros() {
		return livros;
	}

	public void setLivros(Livro livro) {
		this.livros.add(livro);
	}

	public LocalDate getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(LocalDate emprestimo) {
		this.emprestimo = emprestimo;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
