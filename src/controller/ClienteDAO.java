package controller;

import java.util.List;

import entity.Cliente;

public interface ClienteDAO {
	void salvar(Cliente c);
	List<Cliente> lerTodos();
	List<Cliente> pesquisarNome(String nome);
}
