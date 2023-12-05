package controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.List;
import entity.Editora;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ControllerEditora {
	
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty localizacao  = new SimpleStringProperty("");
	private ListProperty livro = new SimpleListProperty(null);

	private ObservableList<Editora> listaEditora = FXCollections.observableArrayList();

	private EditoraDAO editoraDAO = new EditoraDAOImpl();

	public IntegerProperty idProperty() {
		
		return this.id;
		
	}
	
	public StringProperty nomeProperty() {
		
		return this.nome;
		
	}
	
	public StringProperty localizacaoProperty() {
		
		return this.localizacao;
		
	}
	
	public ListProperty livroProperty() {
		
		return this.livro;
		
	}

	public ObservableList<Editora> getLista() {
		
		return this.listaEditora;
		
	}

	public void salvar() {
		
		Editora ed = new Editora();
		ed.setId(id.get());
		ed.setNome(nome.get());
		ed.setLocalizacao(localizacao.get());
		
		editoraDAO.salvar(ed);
		lerTodos();

	}
	
	public void lerTodos() {
		
		List<Editora> editoras = editoraDAO.lerTodos();
		listaEditora.clear();
		listaEditora.addAll(editoras);
		
	}
	
	public void ler() {
		
		List<Editora> editoras = editoraDAO.pesquisarNome(nome.get());
		editoras.clear();
		editoras.addAll(editoras);
		
	}
}