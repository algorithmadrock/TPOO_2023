package controller;

import java.time.LocalDate;
import java.util.List;

import entity.Devolucao;
import javafx.beans.property.BooleanProperty;
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

public class ControlDevolucao {
	
	private IntegerProperty id;
	private ObjectProperty<LocalDate> Data;
	private FloatProperty multa;
	private BooleanProperty status;
	private ObservableList<Devolucao> lista;
	private DAODevolucao devolucaoDAO;
	 
	public ControlDevolucao() {
		id = new SimpleIntegerProperty(0);
		Data = new SimpleObjectProperty<>(LocalDate.now());
		multa = new SimpleFloatProperty(0);
		multa = new SimpleFloatProperty(0);
		devolucaoDAO = new DAOImplDevolucao();
		lista = FXCollections.observableArrayList();
	}
	
	public IntegerProperty idProperty() {
		return this.id;
	}
	public ObjectProperty<LocalDate> dataProperty() {
		return this.Data;
	}
	public FloatProperty multaProperty() {
		return this.multa;
	}
	public BooleanProperty statusProperty() {
		return this.status;
	}
	public ObservableList<Devolucao> getLista() { 
		return this.lista;
	}
	public void salvar() { 
		Devolucao dev = new Devolucao();
		dev.setId(this.id.get());
		dev.setData(this.Data.get());
		dev.setMulta(this.multa.get());
		dev.setStatus(this.status.get());
		
		devolucaoDAO.salvar(dev);
		lerTodos();
	}
	public void lerTodos() { 
		List<Devolucao> devolucoes = devolucaoDAO.lerTodos();
		lista.clear();
		lista.addAll( devolucoes );
	}
	public void ler() { 
		List<Devolucao> devolucao = devolucaoDAO.pesquisarId(id.get());
		lista.clear();
		lista.addAll( devolucao );
	}	
}