package controller;

import java.util.List;

import entity.Categoria;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerCategoria {
		private IntegerProperty id = new SimpleIntegerProperty(0);
		private StringProperty nome = new SimpleStringProperty("");
		private StringProperty desc = new SimpleStringProperty("");
		private ObservableList <Categoria> lista = FXCollections.observableArrayList();

	    private CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
		
		public IntegerProperty idProperty() {
			return this.id;
		}
		
		public StringProperty nomeProperty() {
			return this.nome;
		}
		
		public StringProperty descProperty() {
			return this.desc;
		}
		
		public ObservableList <Categoria> getLista(){
			return this.lista;
		}
		
		public void Salvar() {
			Categoria c = new Categoria ();
			c.setId(id.get());
			c.setNome(nome.get());
			c.setDescricao(desc.get());
			
			categoriaDAO.salvar(c);
			lerTodos();
		}
		
		public void lerTodos () {
			List <Categoria> categoria = categoriaDAO.lerTodos();
			lista.clear();
			lista.addAll(categoria);
		}
		
		public void ler() {
			List <Categoria> categoria = categoriaDAO.pesquisarNome(nome.get());
			lista.clear();
			lista.addAll(categoria);
		}
		
}
