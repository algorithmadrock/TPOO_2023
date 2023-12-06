package controller;

import java.time.LocalDate;
import java.util.List;

import entity.Autor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerAutor {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty nacionalidade = new SimpleStringProperty("");
	private ObjectProperty <LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());
	private ObservableList<Autor> lista = FXCollections.observableArrayList();
	private AutorDAO autorDAO = new AutorDAOImpl();	
	
	public IntegerProperty idProperty() {
		return this.id;
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public StringProperty nacionalidadeProperty() {
		return this.nacionalidade;
	}
	
	public ObjectProperty<LocalDate> nascimentoProperty(){
		return this.nascimento;
	}
	
	public ObservableList<Autor> getLista(){
		return this.lista;
	}
	
	public void salvar() {
		Autor a = new Autor();
		a.setId(id.get());
		a.setNome(nome.get());
		a.setNacionalidade(nacionalidade.get());
		a.setDataNascimento(nascimento.get());
		
		autorDAO.salvar(a);
		lerTodos();
		
	}
	
	public void lerTodos() {
		List<Autor> autores = autorDAO.lerTodos();
		lista.clear();
		lista.addAll(autores);
	}
	
	public void ler() {
		List <Autor> autores = autorDAO.pesquisarNome(nome.get());
		lista.clear();
		lista.addAll(autores);
	}
	
}
