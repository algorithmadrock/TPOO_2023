package controller;

import java.util.List;

import entity.Fornecedor;

public interface FornecedorDAO {
	void salvar(Fornecedor f);

	List<Fornecedor> lerTodos();

	List<Fornecedor> pesquisarNome(String nome);
}
