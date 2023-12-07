package view;

import java.time.format.DateTimeFormatter;

import controller.ControllerFuncionario;
import entity.Funcionario;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class TelaFuncionario implements TelaMudar {
	private BorderPane panPrincipal = new BorderPane();
	private TableView<Funcionario> table = new TableView<>();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDtNascimento = new TextField();
	private TextField txtCargo = new TextField();
	private TextField txtDtContratacao = new TextField();
	private TextField txtSalario = new TextField();
	private TextField txtCpf = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private ControllerFuncionario control = new ControllerFuncionario();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void generateBindings() {
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		Bindings.bindBidirectional(txtDtNascimento.textProperty(), control.dtNascimentoProperty(), localDtf);
		Bindings.bindBidirectional(txtCargo.textProperty(), control.cargoProperty());
		Bindings.bindBidirectional(txtDtContratacao.textProperty(), control.dtContratacaoProperty(), localDtf);
		Bindings.bindBidirectional(txtSalario.textProperty(), control.salarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtCpf.textProperty(), control.cpfProperty());
	}

	public void generateTable() {

		table.setItems(control.getLista());

		TableColumn<Funcionario, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Funcionario, Integer>("id"));

		TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));

		TableColumn<Funcionario, String> colDtNascimento = new TableColumn<>("Data Nascimento");
		colDtNascimento.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getDtNascimento())));

		TableColumn<Funcionario, String> colCargo = new TableColumn<>("Cargo");
		colCargo.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCargo()));

		TableColumn<Funcionario, String> colDtContratacao = new TableColumn<>("Data Contratacao");
		colDtContratacao.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getDtContratacao())));

		TableColumn<Funcionario, Float> colSalario = new TableColumn<>("Salario");
		colSalario.setCellValueFactory(new PropertyValueFactory<Funcionario, Float>("Funcionario"));

		TableColumn<Funcionario, String> colCpf = new TableColumn<>("CPF");
		colCpf.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCpf()));

		table.getColumns().addAll(colId, colNome, colDtNascimento, colCargo, colDtContratacao, colSalario, colCpf);
	}

	public Pane render() {
		txtId.setEditable(false);

		GridPane panFormulario = new GridPane();

		panFormulario.add(new Label("Id: "), 0, 0);
		panFormulario.add(txtId, 1, 0);
		panFormulario.add(new Label("Nome: "), 0, 1);
		panFormulario.add(txtNome, 1, 1);
		panFormulario.add(new Label("Data Nascimento: "), 0, 2);
		panFormulario.add(txtDtNascimento, 1, 2);
		panFormulario.add(new Label("Cargo: "), 0, 3);
		panFormulario.add(txtCargo, 1, 3);
		panFormulario.add(new Label("Data Contratacao: "), 0, 4);
		panFormulario.add(txtDtContratacao, 1, 4);
		panFormulario.add(new Label("Salario: "), 0, 5);
		panFormulario.add(txtSalario, 1, 5);
		panFormulario.add(new Label("Cpf: "), 0, 6);
		panFormulario.add(txtCpf, 1, 6);
		panFormulario.add(btnAdicionar, 0, 7);
		panFormulario.add(btnPesquisar, 1, 7);

		btnAdicionar.setOnAction(e -> control.salvar());
		btnPesquisar.setOnAction(e -> control.ler());

		panPrincipal.setCenter(table);
		panPrincipal.setTop(panFormulario);

		generateBindings();
		generateTable();
		return panPrincipal;
	}

	@Override
	public Pane renderizaPainel() {
		return render();
	}

	@Override
	public TableView retornaTabela() {
		return table;
	}

}
