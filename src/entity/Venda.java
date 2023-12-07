package entity;

import java.time.LocalDate;
import java.util.Set;

public class Venda {

	private int id;
	private int func;
	private int clie;
	private LocalDate data;
	private float valor;
	private Set<Livro> itens;

	public Venda() {
		id++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFunc() {
		return func;
	}

	public void setFunc(Funcionario funcionario) {
		this.func = funcionario.getId();
	}

	public int getClie() {
		return clie;
	}

	public void setClie(Cliente cliente) {
		this.clie = cliente.getId();
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;

	}

	public float getValor() {
		return valor;
	}

	public void setValor() {
		Livro[] livros = (Livro[]) itens.toArray();
		float valor = (float) 0.00;
		for (Livro livro : livros) {
			valor += livro.getValor();
		}
		this.valor = valor;
	}
	
	public void setValor(float valor) {
		this.valor=valor;
	}


	public Set<Livro> getItens() {
		return itens;
	}

	public void setItens(Livro livro) {
		this.itens.add(livro);
	}

}
