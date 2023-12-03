package entity;

import java.util.Set;

public class Autor extends Pessoa {
	private int id;
	private String nacionalidade;
	private Set<Livro> obras;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public Set<Livro> getObras() {
		return obras;
	}

	public void setObras(Set<Livro> obras) {
		this.obras = obras;
	}
}
