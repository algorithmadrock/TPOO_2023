package view;

import controller.ControllerCategoria;
import entity.Categoria;
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
import javafx.util.converter.NumberStringConverter;

public class TelaCategoria implements TelaMudar {
	private TableView<Categoria> table = new TableView<>();
	private TextField txtID = new TextField();
	private TextField txtDesc = new TextField();
	private TextField txtNome = new TextField();
	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private ControllerCategoria control = new ControllerCategoria();

	public void generateBindings() {
		Bindings.bindBidirectional(txtID.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtDesc.textProperty(), control.descProperty());
	}

	public void generateTable() {
		table.setItems(control.getLista());

		TableColumn<Categoria, Integer> colID = new TableColumn<>("Id");
		colID.setCellValueFactory(new PropertyValueFactory<Categoria, Integer>("Id"));

		TableColumn<Categoria, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));

		TableColumn<Categoria, String> colDesc = new TableColumn<>("Descrição");
		colDesc.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescricao()));

		table.getColumns().addAll(colID, colNome, colDesc);
	}

	@Override
	public Pane renderizaPainel() {
		BorderPane panPrincipal = new BorderPane();

		txtID.setEditable(false);

		GridPane panFormulario = new GridPane();
		panFormulario.add(new Label("Id: "), 0, 0);
		panFormulario.add(txtID, 1, 0);
		panFormulario.add(new Label("Nome: "), 0, 1);
		panFormulario.add(txtNome, 1, 1);
		panFormulario.add(new Label("Descrição: "), 0, 2);
		panFormulario.add(txtDesc, 1, 2);
		panFormulario.add(btnAdicionar, 0, 3);
		panFormulario.add(btnPesquisar, 1, 3);

		btnAdicionar.setOnAction(e -> control.Salvar());
		btnPesquisar.setOnAction(e -> control.ler());

		panPrincipal.setCenter(table);
		panPrincipal.setTop(panFormulario);

		generateBindings();
		generateTable();

		return panPrincipal;
	}

	@Override
	public TableView retornaTabela() {
		// TODO Auto-generated method stub
		return table;
	}

}
