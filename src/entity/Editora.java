package entity;

import java.util.Set;

public class Editora {
	private int id;
	private String nome;
	private String localizacao;
	private Set<Livro> catalogo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Set<Livro> getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Set<Livro> catalogo) {
		this.catalogo = catalogo;
	}
}
