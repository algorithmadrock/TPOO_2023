package view;

import java.time.format.DateTimeFormatter;

import controller.ControllerCliente;
import controller.ControllerVenda;
import entity.Livro;
import entity.Venda;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyFloatWrapper;
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
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class TelaVenda implements TelaMudar{
	public TelaVenda() {
		criaPainel();
		criaTabelaVenda();
		criaTabelaLivro();
		generateBindings();
	}
	
	private BorderPane painelGeral = new BorderPane();
	private TextField txtId = new TextField();
	private TextField txtFunc = new TextField();
	private TextField txtCliente = new TextField();
	private TextField txtData = new TextField();
	private TextField txtValor = new TextField();
	private TextField txtLivro = new TextField();
	private Button botaoSalvar = new Button("Salvar");
	private Button botaoPesquisar = new Button("Pesquisar");
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private TableView<Livro> livros = new TableView<>();
	private TableView<Venda> vendas = new TableView<>();
	private ControllerVenda cv = new ControllerVenda();
	
	private void criaTabelaLivro() {
		
		TableColumn<Livro, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(
				new PropertyValueFactory<Livro, Integer>("Id"));
		TableColumn<Livro, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		livros.getColumns().addAll(colId, colNome);
	}
	private void criaTabelaVenda() {
		
		TableColumn<Venda, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(
				new PropertyValueFactory<Venda, Integer>("id"));
		TableColumn<Venda, Integer> colFunc = new TableColumn<>("Funcionario");
		colFunc.setCellValueFactory(
				new PropertyValueFactory<Venda, Integer>("idfunc"));
		TableColumn<Venda, Integer> colCli = new TableColumn<>("Cliente");
		colCli.setCellValueFactory(
				new PropertyValueFactory<Venda, Integer>("idclie"));
		TableColumn<Venda, String> colData = new TableColumn<>("Data");
		colData.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(dtf.format(itemData.getValue().getData())));
		TableColumn<Venda, Float> colValor = new TableColumn<>("Valor");
		colValor.setCellValueFactory(
				new PropertyValueFactory<Venda, Float>("valor"));
		vendas.getColumns().addAll(colId, colFunc, colCli, colData, colValor);
	}
	private void generateBindings() {
		Bindings.bindBidirectional(txtId.textProperty(), cv.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtFunc.textProperty(), cv.funcionarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtCliente.textProperty(), cv.clienteProperty(), new NumberStringConverter());
		LocalDateStringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		Bindings.bindBidirectional(txtData.textProperty(), cv.dataProperty(), localDtf);
		Bindings.bindBidirectional(txtValor.textProperty(), cv.valorProperty(), new NumberStringConverter());

		
	}
	private void criaPainel() {
		GridPane painelFormulario = new GridPane();
		painelFormulario.add(new Label("ID: "), 0, 0);
		painelFormulario.add(txtId, 1, 0);
		painelFormulario.add(new Label("Funcionario: "), 0, 1);
		painelFormulario.add(txtFunc, 1, 1);
		painelFormulario.add(new Label("Clinte: "), 0, 2);
		painelFormulario.add(txtCliente, 1, 2);
		painelFormulario.add(new Label("Data: "), 0, 3);
		painelFormulario.add(txtData, 1, 3);
		painelFormulario.add(new Label("Valor: "), 0, 4);
		painelFormulario.add(txtValor, 1, 4);
		painelFormulario.add(new Label("Livro: "), 0, 5);
		painelFormulario.add(txtLivro, 1, 5);
		painelFormulario.add(botaoSalvar, 0, 6);
		painelFormulario.add(botaoPesquisar, 1, 6);
		
		botaoSalvar.setOnAction(ct-> cv.salvarCliente());
		
		painelGeral.setCenter(painelFormulario);
		painelGeral.setRight(livros);
		painelGeral.setBottom(vendas);
		
	}	
	@Override
	public Pane renderizaPainel() {
		// TODO Auto-generated method stub
		return painelGeral;
	}

	@Override
	public TableView retornaTabela() {
		// TODO Auto-generated method stub
		return null;
	}

}
