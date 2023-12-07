package controller;

import java.util.List;

import entity.Venda;

public interface VendaDAO {
	void salvarVenda(Venda v);
	List<Venda> lerTodos();
	List<Venda> pesquisarNome(String nome);
}
