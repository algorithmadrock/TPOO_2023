package controller;

import java.time.LocalDate;
import java.util.List;

import entity.Cliente;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerCliente {

	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty cpf = new SimpleStringProperty();
	private StringProperty telefone = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty endereco = new SimpleStringProperty();

	
	private ClienteDAO bd = new ClienteDAOimp();
	
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	
	public ObservableList<Cliente> getLista() {
		lerTodos();
		return clientes;
	}
	
	public void lerTodos() {
		List<Cliente> cli = bd.lerTodos();
		clientes.clear();
		clientes.addAll(cli);
	}
	
	public void salvarCliente() {
		Cliente c = new Cliente();
		c.setId(id.getValue());
		c.setNome(nome.getValue());
		c.setDtNascimento(nascimento.getValue());
		c.setCpf(cpf.getValue());
		c.setEndereco(endereco.getValue());
		c.setTelefone(telefone.getValue());
		c.setEmail(email.getValue());
		c.setEndereco(endereco.getValue());
		bd.salvar(c);
		lerTodos();
	}
	
	public ObservableList<Cliente> pesquisarNome(){
		List<Cliente> cli = bd.pesquisarNome(nome.getValue());
		clientes.clear();
		clientes.addAll(cli);
		return clientes;
	}
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public ObjectProperty<LocalDate> nascimentoProperty(){
		return nascimento;
	}
	
	public StringProperty cpfProperty() {
		return cpf;
	}
	
	
	public StringProperty telefoneProperty() {
		return telefone;
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public StringProperty enderecoProperty() {
		return endereco;
	}
	
}
