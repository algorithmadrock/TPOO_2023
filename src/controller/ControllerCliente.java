package controller;

import java.time.LocalDate;

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

	/*
	private TextField txtId = new TextField("ID");
	private TextField txtNome = new TextField("Nome");
	private TextField txtNascimento = new TextField("Data de nascimento");
	private TextField txtCPF = new TextField("CPF");
	private TextField txtEndereco= new TextField("Endereco");
	private TextField txtTelefone = new TextField("Telefone");
	private TextField txtEmail = new TextField("Email");
	private TableView<Cliente> tabela = new TableView<>();
	*/
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>();
	private StringProperty cpf = new SimpleStringProperty();
	private StringProperty endereco = new SimpleStringProperty();
	private StringProperty telefone = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	
	private ClienteDAO bd = new ClienteDAOimp();
	
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	
	public ObservableList<Cliente> getLista() {
		clientes.addAll(bd.pesquisarNome(null));
		return clientes;
	}
	
	public void SalvarCliente() {
		Cliente c = new Cliente();
		c.setId(id.getValue());
		c.setNome(nome.getValue());
		c.setDataNascimento(nascimento.getValue());
		c.setCpf(cpf.getValue());
		c.setEndereco(endereco.getValue());
		c.setTelefone(telefone.getValue());
		c.setEmail(email.getValue());
		bd.salvar(c);
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
	
	public StringProperty enderecoProperty() {
		return endereco;
	}
	
	public StringProperty telefoneProperty() {
		return telefone;
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	
}
