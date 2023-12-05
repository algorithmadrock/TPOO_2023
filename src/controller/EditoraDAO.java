package controller;

import java.util.List;

import entity.Editora;

public interface EditoraDAO {
	
	void salvar(Editora e);
	List<Editora> lerTodos();
	List<Editora> pesquisarNome(String nome);

}