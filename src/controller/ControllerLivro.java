package controller;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import java.util.List;
import entity.Autor;
import entity.Editora;
import entity.Livro;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ControllerLivro {

	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private ObjectProperty autor  = new SimpleObjectProperty("");
	private ObjectProperty editora = new SimpleObjectProperty("");
	private BooleanProperty disponibilidade  = new SimpleBooleanProperty(false);
	private StringProperty genero = new SimpleStringProperty("");
	private FloatProperty valor = new SimpleFloatProperty(0.0f);
	private ObjectProperty<LocalDate> ano = new SimpleObjectProperty<>(LocalDate.now());

	private ObservableList<Livro> listaLivro = FXCollections.observableArrayList();

	private LivroDAO livroDAO = new LivroDAOImpl();

	public IntegerProperty idProperty() {
		
		return this.id;
		
	}
	
	public StringProperty nomeProperty() {
		
		return this.nome;
		
	}
	
	public ObjectProperty autorProperty() {
		
		return this.autor;
		
	}
	
	public ObjectProperty editoraProperty() {
		
		return this.editora;
		
	}

	public BooleanProperty disponibilidadeProperty() {

		return this.disponibilidade;
		
	}

	public StringProperty generoProperty() {
		
		return this.genero;
		
	}
	
	public FloatProperty valorProperty() {
		
		return this.valor;
		
	}
	
	public ObjectProperty<LocalDate> anoProperty() {
		
		return this.ano;
		
	}

	public ObservableList<Livro> getLista() {
		
		return this.listaLivro;
		
	}

	public void salvar() {
		
		Livro l = new Livro();
		l.setId(id.get());
		l.setNome(nome.get());
		
		Autor aut = new Autor();
		aut.setId(id.get());
		l.setAutor(aut);
		
		Editora edi = new Editora();
		edi.setId(id.get());
		l.setEditora(edi);
		
		l.setDisponibilidade(disponibilidade.get());
		l.setGenero(genero.get());
		l.setValor(valor.get());
		l.setAno(ano.get());
		
		livroDAO.salvar(l);
		lerTodos();

	}
	
	public void lerTodos() {
		
		List<Livro> livros = livroDAO.lerTodos();
		listaLivro.clear();
		listaLivro.addAll(livros);
		
	}
	
	public void ler() {
		
		List<Livro> livros = livroDAO.pesquisarNome(nome.get());
		livros.clear();
		livros.addAll(livros);
		
	}
}