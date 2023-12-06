package controller;

import java.util.Set;

import entity.Funcionario;

public interface FuncionarioDAO {
	void salvar(Funcionario f);

	Set<Funcionario> lerTodos();

	Set<Funcionario> pesquisarNome(String nome);
}
