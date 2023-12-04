package controller;

import java.util.List;

import entity.Emprestimo;

public interface DAOEmprestimo {
	void salvar(Emprestimo emp);
	List<Emprestimo> lerTodos();
	List<Emprestimo> pesquisarId(int id);

}