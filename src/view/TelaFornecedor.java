package view;

import controller.ControllerFornecedor;
import entity.Fornecedor;
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
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;

public class TelaFornecedor implements TelaMudar {
	private BorderPane panPrincipal = new BorderPane();
	private TableView<Fornecedor> table = new TableView<>();
	private TextField txtId = new TextField();
	private TextField txtCnpj = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtStatus = new TextField();
	private Button btnAdicionar = new Button("Adicionar fornecedor");
	private Button btnPesquisar = new Button("Pesquisar fornecedor");
	private ControllerFornecedor control = new ControllerFornecedor();

	public void generateBindings() {
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtCnpj.textProperty(), control.cnpjProperty());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtStatus.textProperty(), control.statusProperty(), new BooleanStringConverter());
	}

	public void generateTable() {
		table.setItems(control.getLista());

		TableColumn<Fornecedor, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Fornecedor, Integer>("Id"));

		TableColumn<Fornecedor, String> colCnpj = new TableColumn<>("CNPJ");
		colCnpj.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCnpj()));

		TableColumn<Fornecedor, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));

		TableColumn<Fornecedor, Boolean> colStatus = new TableColumn<>("Status");
		colStatus.setCellValueFactory(new PropertyValueFactory<Fornecedor, Boolean>("Status"));

		table.getColumns().addAll(colId, colCnpj, colNome, colStatus);
	}

	@Override
	public Pane renderizaPainel() {

		txtId.setEditable(false);

		GridPane panFormulario = new GridPane();
		panFormulario.add(new Label("Id: "), 0, 0);
		panFormulario.add(txtId, 1, 0);
		panFormulario.add(new Label("Cnpj: "), 0, 1);
		panFormulario.add(txtCnpj, 1, 1);
		panFormulario.add(new Label("Nome: "), 0, 2);
		panFormulario.add(txtNome, 1, 2);
		panFormulario.add(new Label("Status: "), 0, 3);
		panFormulario.add(txtStatus, 1, 3);
		panFormulario.add(btnAdicionar, 0, 4);
		panFormulario.add(btnPesquisar, 1, 4);

		btnAdicionar.setOnAction(e -> control.salvar());
		btnPesquisar.setOnAction(e -> control.ler());

		panPrincipal.setCenter(table);
		panPrincipal.setTop(panFormulario);

		generateBindings();
		generateTable();
		return panPrincipal;
	}

	@Override
	public TableView retornaTabela() {
		return table;
	}

}
