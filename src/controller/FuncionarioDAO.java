package controller;

import java.util.List;

import entity.Funcionario;

public interface FuncionarioDAO {
	void salvar(Funcionario f);

	List<Funcionario> lerTodos();

	List<Funcionario> pesquisarNome(String nome);
}
