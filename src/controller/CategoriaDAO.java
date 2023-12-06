package controller;

import java.util.List;

import entity.Categoria;

public interface CategoriaDAO {
	void salvar(Categoria c);
	List <Categoria> lerTodos();
	List <Categoria> pesquisarNome (String nome);

}
