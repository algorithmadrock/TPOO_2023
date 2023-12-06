package view;

import java.time.format.DateTimeFormatter;

import controller.ControllerAutor;
import entity.Autor;
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

public class TelaAutor implements TelaMudar{
	private TableView<Autor> table = new TableView<>();
	private TextField txtID = new TextField ();	
	private TextField txtNome = new TextField ();
	private TextField txtNacionalidade = new TextField();
	private TextField txtNascimento = new TextField ();
	private Button btnAdicionar = new Button ("Adicionar");
	private Button btnPesquisar = new Button ("Pesquisar");
	private ControllerAutor control = new ControllerAutor();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void generateBindings() {
		Bindings.bindBidirectional(txtID.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
		Bindings.bindBidirectional(txtNacionalidade.textProperty(), control.nacionalidadeProperty());
		StringConverter localDtf = new LocalDateStringConverter(dtf,dtf);
		Bindings.bindBidirectional(txtNascimento.textProperty(), control.nascimentoProperty(), localDtf);
	}
	
	public void generateTable() {
		table.setItems(control.getLista());
		
		TableColumn<Autor, Integer> colID = new TableColumn("Id");
		colID.setCellValueFactory(new PropertyValueFactory<Autor,Integer>("id"));
		
		TableColumn<Autor, String> colNome = new TableColumn("Nome");
		colNome.setCellValueFactory( itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		
		TableColumn<Autor, String> colNacionalidade = new TableColumn ("Nacionalidade");
		colNacionalidade.setCellValueFactory( itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNacionalidade()));
		
		TableColumn<Autor, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getDataNascimento())));
		
		table.getColumns().addAll(colID, colNome, colNacionalidade, colNascimento);
	}
	
	
	
	@Override
	public Pane renderizaPainel() {
		BorderPane panPrincipal = new BorderPane();
		
		txtID.setEditable(false);
		
		GridPane panFormulario = new GridPane();
		panFormulario.add(new Label ("Id: "), 0, 0);
		panFormulario.add(txtID, 1, 0);
		panFormulario.add(new Label("Nome: "), 0, 1);
		panFormulario.add(txtNome, 1, 1);
		panFormulario.add(new Label("Nacionalidade: "), 0, 2);
		panFormulario.add(txtNacionalidade, 1, 2);
		panFormulario.add(new Label("Nascimento: "), 0, 3);
		panFormulario.add(txtNascimento, 1, 3);
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
		// TODO Auto-generated method stub
		return table;
	}

}
