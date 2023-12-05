package controller;

import java.util.Set;

import entity.Fornecedor;

public interface FornecedorDAO {
	void salvar(Fornecedor f);

	Set<Fornecedor> lerTodos();

	Set<Fornecedor> pesquisarNome(String nome);
}
