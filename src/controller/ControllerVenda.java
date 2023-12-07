package controller;

import java.time.LocalDate;
import java.util.List;

import entity.Cliente;
import entity.Funcionario;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerVenda {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty funcionario = new SimpleStringProperty("");
	private StringProperty cliente = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();
	private FloatProperty valor = new SimpleFloatProperty();
	private IntegerProperty idLivro = new SimpleIntegerProperty(0);
	private ListProperty<Livro> livros = new SimpleListProperty<>();
	private ObservableList<Venda> vendas= FXCollections.observableArrayList();
	
	private VendaDAOimp venDao = new VendaDAOimp();
	private ClienteDAOimp cliDAO = new ClienteDAOimp();
	private FuncionarioDAOImpl funDAO = new FuncionarioDAOImpl();
	
	public IntegerProperty idProperty() {
		return id;
	}
	public StringProperty funcionarioProperty() {
		return funcionario;
	}
	public StringProperty clienteProperty() {
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
	
	public ObservableList<Venda> getLista(){
		lerTodos();
		return vendas;
	}
	
	public void salvarCliente() {
		Venda v = new Venda();
		v.setFunc(funDAO.pesquisarNome(funcionario.getValue()).iterator().next());
		v.setClie(cliDAO.pesquisarNome(cliente.getValue()).get(0));
		v.setData(data.getValue());
		v.setValor(valor.getValue());
		venDao.salvarVenda(v);
		lerTodos();
	}
	
	public void lerTodos() {
		List<Venda> vend = venDao.lerTodos();
		vendas.clear();
		vendas.addAll(vend);
	}
	public void pesquisarId() {
		vendas.clear();
		List<Venda> listaVendas = venDao.pesquisarId(id.getValue());
		vendas.addAll(listaVendas);
	}
}
