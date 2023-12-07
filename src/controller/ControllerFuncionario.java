package controller;

import java.time.LocalDate;
import java.util.List;

import entity.Funcionario;
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

public class ControllerFuncionario {
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dtNascimento = new SimpleObjectProperty<>();
	private StringProperty cargo = new SimpleStringProperty();
	private ObjectProperty<LocalDate> dtContratacao = new SimpleObjectProperty<>();
	private StringProperty cpf = new SimpleStringProperty();
	private FloatProperty salario = new SimpleFloatProperty();

	private ObservableList<Funcionario> lista = FXCollections.observableArrayList();

	private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();

	public IntegerProperty idProperty() {
		return this.id;
	}

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public ObjectProperty<LocalDate> dtNascimentoProperty() {
		return this.dtNascimento;
	}

	public StringProperty cargoProperty() {
		return this.cargo;
	}

	public ObjectProperty<LocalDate> dtContratacaoProperty() {
		return this.dtContratacao;
	}

	public StringProperty cpfProperty() {
		return this.cpf;
	}

	public FloatProperty salarioProperty() {
		return this.salario;
	}

	public ObservableList<Funcionario> getLista() {
		return this.lista;
	}

	public void salvar() {
		Funcionario f = new Funcionario();
		f.setId(id.get());
		f.setNome(nome.get());
		f.setDtNascimento(dtNascimento.get());
		f.setCargo(cargo.get());
		f.setDtContratacao(dtContratacao.get());
		f.setCpf(cpf.get());
		f.setSalario(salario.get());

		funcionarioDAO.salvar(f);
	}

	public void lerTodos() {
		List<Funcionario> funcionarios = funcionarioDAO.lerTodos();
		lista.clear();
		lista.addAll(funcionarios);
	}

	public void ler() {
		List<Funcionario> funcionarios = funcionarioDAO.pesquisarNome(nome.get());
		lista.clear();
		lista.addAll(funcionarios);
	}
}
