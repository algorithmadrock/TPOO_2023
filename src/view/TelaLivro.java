package view;

import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import java.time.format.DateTimeFormatter;
import controller.ControllerLivro;
import entity.Livro;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.beans.binding.Bindings;
import javafx.util.converter.NumberStringConverter;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.StringConverter;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

public class TelaLivro implements TelaMudar {

	private TableView<Livro> table = new TableView<>();
	private TextField txtId = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtAutor = new TextField();
	private TextField txtEditora = new TextField();
	private TextField txtAno = new TextField();
	private TextField txtDisponibilidade = new TextField();
	private TextField txtGenero = new TextField();
	private TextField txtValor = new TextField();
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnSalvar = new Button("Salvar");
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private ControllerLivro control = new ControllerLivro();

	public void generateBindings() {

		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtAutor.textProperty(), control.autorProperty());
		Bindings.bindBidirectional(txtEditora.textProperty(), control.editoraProperty());
		Bindings.bindBidirectional(txtDisponibilidade.textProperty(), control.disponibilidadeProperty(), new BooleanStringConverter());
		Bindings.bindBidirectional(txtGenero.textProperty(), control.generoProperty());
		Bindings.bindBidirectional(txtValor.textProperty(), control.valorProperty(), new NumberStringConverter());
		StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		Bindings.bindBidirectional(txtAno.textProperty(), control.anoProperty(), localDtf);

	}

	public void generateTable() {

		table.setItems(control.getLista());
		
		TableColumn<Livro, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("Id"));

		TableColumn<Livro, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		
		TableColumn<Livro, Object> colAutor = new TableColumn<>("Autor");
		colAutor.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper(itemData.getValue().getAutor().getNome()));
		
		TableColumn<Livro, Object> colEditora = new TableColumn<>("Editora");
		colEditora.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper(itemData.getValue().getEditora().getNome()));
		
		TableColumn<Livro, Boolean> colDisponibilidade = new TableColumn<>("Disponibilidade");
		colDisponibilidade.setCellValueFactory(new PropertyValueFactory<Livro, Boolean>("Disponibilidade"));
		
		TableColumn<Livro, String> colGenero = new TableColumn<>("Genero");
		colGenero.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getGenero()));
		
		TableColumn<Livro, Float> colValor = new TableColumn<>("Valor");
		colValor.setCellValueFactory((new PropertyValueFactory<Livro, Float>("Valor")));

		TableColumn<Livro, String> colAno = new TableColumn<>("Ano");
		colAno.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getAno())));

		table.getColumns().addAll(colId, colNome, colAutor, colEditora, colDisponibilidade, colGenero, colValor, colAno);

	}

	public Pane render() {

		BorderPane panPrincipal = new BorderPane();

		txtId.setEditable(false);

		GridPane panFormulario = new GridPane();
		
		panFormulario.add(new Label("Id: "), 0, 0);
		panFormulario.add(txtId, 1, 0);
		panFormulario.add(new Label("Nome: "), 0, 1);
		panFormulario.add(txtNome, 1, 1);
		panFormulario.add(new Label("Autor: "), 0, 2);
		panFormulario.add(txtAutor, 1, 2);
		panFormulario.add(new Label("Editora: "), 0, 3);
		panFormulario.add(txtEditora, 1, 3);
		panFormulario.add(new Label("Ano: "), 0, 4);
		panFormulario.add(txtAno, 1, 4);
		panFormulario.add(new Label("Disponibilidade: "), 0, 5);
		panFormulario.add(txtDisponibilidade, 1, 5);
		panFormulario.add(new Label("Genero: "), 0, 6);
		panFormulario.add(txtGenero, 1, 6);
		panFormulario.add(new Label("Valor: "), 0, 7);
		panFormulario.add(txtValor, 1, 7);
		panFormulario.add(btnSalvar, 0, 8);
		panFormulario.add(btnPesquisar, 1, 8);

		btnSalvar.setOnAction(e -> control.salvar());
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