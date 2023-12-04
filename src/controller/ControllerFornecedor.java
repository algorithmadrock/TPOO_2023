package controller;

import java.util.Set;

import entity.Fornecedor;
import entity.Livro;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerFornecedor {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty cnpj = new SimpleStringProperty();
	private StringProperty nome = new SimpleStringProperty();
	private BooleanProperty status = new SimpleBooleanProperty();
	private SetProperty<Livro> livros = new SimpleSetProperty<>();

	private ObservableList<Fornecedor> lista = FXCollections.observableArrayList();

	private FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();

	public IntegerProperty idProperty() {
		return this.id;
	}

	public StringProperty cnpjProperty() {
		return this.cnpj;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public BooleanProperty statusProperty() {
		return this.status;
	}

	public SetProperty<Livro> livrosProperty() {
		return this.livros;
	}

	public ObservableList<Fornecedor> getLista() {
		return this.lista;
	}

	public void salvar() {
		Fornecedor f = new Fornecedor();
		f.setId(id.get());
		f.setCnpj(cnpj.get());
		f.setNome(nome.get());
		f.setStatus(status.get());
		f.setLivros(livros.get());

		fornecedorDAO.salvar(f);
		lerTodos();
	}

	public void lerTodos() {
		Set<Fornecedor> fornecedores = fornecedorDAO.lerTodos();
		lista.clear();
		lista.addAll(fornecedores);
	}

	public void ler() {
		Set<Fornecedor> fornecedores = fornecedorDAO.pesquisarNome(nome.get());
		lista.clear();
		lista.addAll(fornecedores);
	}
}