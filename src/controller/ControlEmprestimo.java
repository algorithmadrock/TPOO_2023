package controller;

import java.time.LocalDate;
import java.util.List;

import entity.Emprestimo;
import entity.Livro;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControlEmprestimo {
	
	private IntegerProperty id;
	private ObjectProperty<LocalDate> emData;
//	private ObjectProperty<LocalDate> deData;
	private IntegerProperty idClie;
	private StringProperty nmClie;
	private IntegerProperty idLivro;
	private FloatProperty valor;
	private ObservableList<Emprestimo> lista;
	private DAOEmprestimo emprestimoDAO;
	 
	public ControlEmprestimo() {
		id = new SimpleIntegerProperty(0);
		emData = new SimpleObjectProperty<>(LocalDate.now());
//		deData = new SimpleObjectProperty<>(LocalDate.now());
		idClie = new SimpleIntegerProperty(0);
		nmClie  = new SimpleStringProperty("");
		idLivro = new SimpleIntegerProperty(0);
		valor = new SimpleFloatProperty(0);
//		emprestimoDAO = new DAOImplEmprestimo();
		lista = FXCollections.observableArrayList();
	}
	
	public IntegerProperty idProperty() {
		return this.id;
	}
	public ObjectProperty<LocalDate> emDataProperty() {
		return this.emData;
	}
//	public ObjectProperty<LocalDate> deDataProperty() {
//		return this.deData;
//	}
	public IntegerProperty idClieProperty() {
		return this.idClie;
	}
	public StringProperty nmClieProperty() {
		return this.nmClie;
	}
	public IntegerProperty idLivroProperty() {
		return this.idLivro;
	}
	public FloatProperty valorProperty() {
		return this.valor;
	}
	public ObservableList<Emprestimo> getLista() { 
		return this.lista;
	}
	public void salvar() { 
		Emprestimo emp = new Emprestimo();
		emp.setId(this.id.get());
		emp.setEmprestimo(this.emData.get());
//		emp.setDevolucao(this.deData.get());
		emp.setIdClie(this.idClie.get());
		emp.setValor(this.valor.get());
		
		Livro liv = new Livro();
		liv.setId(this.idLivro.get());
		
		emp.setLivros(liv);
		emprestimoDAO.salvar(emp);
		lerTodos();
	}
	public void lerTodos() { 
		List<Emprestimo> emprestimos = emprestimoDAO.lerTodos();
		lista.clear();
		lista.addAll( emprestimos );
	}
	public void ler() { 
		List<Emprestimo> alunos = emprestimoDAO.pesquisarId(id.get());
		lista.clear();
		lista.addAll( alunos );
	}	
}
