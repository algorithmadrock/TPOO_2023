package controller;

import java.time.LocalDate;

import entity.Cliente;
import entity.Livro;
import entity.Venda;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerVenda {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private IntegerProperty funcionario = new SimpleIntegerProperty(0);
	private IntegerProperty cliente = new SimpleIntegerProperty(0);
	private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();
	private FloatProperty valor = new SimpleFloatProperty();
	private IntegerProperty idLivro = new SimpleIntegerProperty(0);
	private ListProperty<Livro> livros = new SimpleListProperty<>();
	private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
	
	public IntegerProperty idProperty() {
		return id;
	}
	public IntegerProperty funcionarioProperty() {
		return funcionario;
	}
	public IntegerProperty clienteProperty() {
		return cliente;
	}
	public ObjectProperty<LocalDate> dataProperty() {
		return data;
	}
	public FloatProperty valorProperty() {
		return valor;
	}
	public IntegerProperty idLivroProperty() {
		return idLivro;
	}
	public ListProperty<Livro> livrosProperty(){
		return livros;
	}
	
	public ObservableList<Cliente> getLista(){
		return clientes;
	}
	
	public void salvarCliente() {
		Venda v = new Venda();
		v.setId(id.getValue());
		v.setIdfunc(funcionario.getValue());
		v.setIdclie(cliente.getValue());
		v.setData(data.getValue());
		v.setItens(null);
		v.setValor();
	}
}
