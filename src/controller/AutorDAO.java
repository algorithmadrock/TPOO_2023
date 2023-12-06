package controller;

import java.util.List;

import entity.Autor;

public interface AutorDAO {

		void salvar(Autor a);
		List<Autor> lerTodos();
		List<Autor> pesquisarNome (String nome);
		
		
}
