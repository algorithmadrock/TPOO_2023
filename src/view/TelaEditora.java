package view;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import java.util.Set;
import controller.ControllerEditora;
import entity.Editora;
import entity.Livro;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.beans.binding.Bindings;
import javafx.util.converter.NumberStringConverter;
import javafx.util.StringConverter;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

public class TelaEditora implements TelaMudar{
	
	private TableView<Editora> tableEditora = new TableView<>();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtLocalizacao = new TextField();
	private TableView<Livro> tableLivro = new TableView<>();
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnSalvar = new Button("Salvar");
	private ControllerEditora control = new ControllerEditora();

	public void generateBindings() {

		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtLocalizacao.textProperty(), control.localizacaoProperty());

	}

	public void generateTable() {

		tableEditora.setItems(control.getLista());
		
		TableColumn<Editora, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Editora, Integer>("id"));

		TableColumn<Editora, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		
		TableColumn<Editora, String> colLocalizacao = new TableColumn<>("Localizacao");
		colLocalizacao.setCellValueFactory(itemData ->	new ReadOnlyObjectWrapper(itemData.getValue().getLocalizacao()));

		tableEditora.getColumns().addAll(colId, colNome, colLocalizacao);

	}

	public Pane render() {

		BorderPane panPrincipal = new BorderPane();

		txtId.setEditable(false);

		GridPane panFormulario = new GridPane();
		
		panFormulario.add(new Label("Id: "), 0, 0);
		panFormulario.add(txtId, 1, 0);
		panFormulario.add(new Label("Nome: "), 0, 1);
		panFormulario.add(txtNome, 1, 1);
		panFormulario.add(new Label("Localizacao: "), 0, 2);
		panFormulario.add(txtLocalizacao, 1, 2);
		panFormulario.add(btnSalvar, 0, 3);
		panFormulario.add(btnPesquisar, 1, 3);

		btnSalvar.setOnAction(e-> control.salvar());
		btnPesquisar.setOnAction(e -> control.ler());

		panPrincipal.setCenter(tableEditora);
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

		return tableEditora;
		
	}

}