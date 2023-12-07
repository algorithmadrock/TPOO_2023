package controller;

import java.util.List;

import entity.Devolucao;

public interface DAODevolucao {
	
	void salvar(Devolucao dev);
	List<Devolucao> lerTodos();
	List<Devolucao> pesquisarId(int id);

}