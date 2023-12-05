package controller;

import java.util.List;

import entity.Livro;

public interface LivroDAO {
	
	void salvar(Livro l);
	List<Livro> lerTodos();
	List<Livro> pesquisarNome(String nome);
	
}