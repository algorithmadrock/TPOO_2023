package entity;

import java.util.Set;

public class Fornecedor {
	private static int id;
	private String cnpj;
	private String nome;
	private boolean status;
	private Set<Livro> livros;
	
	public Fornecedor() {
		id++;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Set<Livro> getLivros() {
		return livros;
	}
	public void setLivros(Livro livro) {
		this.livros.add(livro);
	}
	
	

}
