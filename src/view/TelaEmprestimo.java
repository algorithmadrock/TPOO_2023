package view;

import java.time.format.DateTimeFormatter;

import controller.ControlEmprestimo;
import entity.Emprestimo;
import entity.Livro;
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

public class TelaEmprestimo implements TelaMudar{
	
	private BorderPane painel;
	private ControlEmprestimo control;
	private TableView<Emprestimo> table1;
	private TableView<Livro> table2;
	private DateTimeFormatter dtf;
	
	public TelaEmprestimo() {
		control = new ControlEmprestimo();
		table1 = new TableView<>();
		table2 = new TableView<>();
		dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		painel = new BorderPane();
		
		centertela();
		bottomtela();
	
	}

	public void bottomtela() {
		table1.setItems(control.getLista());
		
		TableColumn<Emprestimo, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Emprestimo, Integer>("id") );
		
		TableColumn<Emprestimo, String> colEmData = new TableColumn<>("Data Emprestimo");
		colEmData.setCellValueFactory( itemData ->	new ReadOnlyStringWrapper( dtf.format(itemData.getValue().getEmprestimo()) ));	
		
		TableColumn<Emprestimo, String> colDeData = new TableColumn<>("Data Devolucao");
		colEmData.setCellValueFactory( itemData ->	new ReadOnlyStringWrapper( dtf.format(itemData.getValue().getDevolucao()) ));	
		
		TableColumn<Emprestimo, Integer> colIdClie = new TableColumn<>("Id Cliente");
		colIdClie.setCellValueFactory(new PropertyValueFactory<Emprestimo, Integer>("id cliente"));
		
		TableColumn<Emprestimo, String> colNmClie = new TableColumn<>("Nome Cliente");
		colNmClie.setCellValueFactory( itemData ->	new ReadOnlyStringWrapper(itemData.getValue().getNmClie()));
		
		TableColumn<Emprestimo, Float> colValor = new TableColumn<>("Valor");
		colValor.setCellValueFactory(new PropertyValueFactory<Emprestimo, Float>("valor total"));
		
		table1.getColumns().addAll(colId, colEmData, colDeData, colIdClie, colNmClie, colValor);
	}
	
	public void centertela() {
		
		GridPane subpainel = new GridPane();
		subpainel.setRowSpan(subpainel, 8);
		subpainel.setColumnSpan(subpainel, 6);
		
		//ELEMENTOS DO CENÁRIO
		Label lblId = new Label("ID #"); 
		subpainel.add(lblId, 1, 1);
		TextField txtId = new TextField();
		subpainel.add(txtId, 1, 2);
		txtId.setEditable(false);
		
		Label lblEmData = new Label("DATA"); 
		subpainel.add(lblEmData, 2, 1);
		TextField txtEmData = new TextField();
		subpainel.add(txtEmData, 2, 2);
		
		Label lblDias = new Label("DIAS"); 
		subpainel.add(lblDias, 2, 3);
		TextField txtDias = new TextField();
		subpainel.add(txtDias, 2, 4);
		
		Label lblDeData = new Label("DEVOLUÇÃO"); 
		subpainel.add(lblDeData, 2, 5);
		TextField txtDeData = new TextField();
		subpainel.add(txtDeData, 2, 6);
		txtDeData.setEditable(false);
		
		Label lblCliente = new Label("DADOS DO CLIENTE"); 
		subpainel.add(lblCliente, 3, 1);
		
		Label lblIdClie = new Label("ID #"); 
		subpainel.add(lblId, 4, 1);
		TextField txtIdClie = new TextField();
		subpainel.add(txtIdClie, 4, 2);
		
		Button busCli = new Button("-O");
		subpainel.add(busCli, 4, 3);
		
		Label lblNmClie = new Label("NOME"); 
		subpainel.add(lblNmClie, 4, 4);
		TextField txtNmClie = new TextField();
		subpainel.add(txtNmClie, 4, 5);
		txtNmClie.setEditable(false);
		
		
		Label lblLivro = new Label("DADOS DOS LIVROS");
		subpainel.add(lblLivro, 5, 1);
		
		Label lblIdLivro = new Label("ID #"); 
		subpainel.add(lblLivro, 6, 1);
		TextField txtIdLivro = new TextField();
		subpainel.add(txtIdLivro, 6, 2);
		
		Button busLi = new Button("-O");
		subpainel.add(busLi, 6, 3);
		
		TextField txtSetLivros = new TextField(); 
		subpainel.add(txtSetLivros, 7, 1);
		
		Label lblValor = new Label("VALOR R$"); 
		subpainel.add(lblValor, 8, 1);
		TextField txtValor = new TextField();
		subpainel.add(txtValor, 8, 2);
		txtValor.setEditable(false);
		
		Button btnsalvar = new Button("Adicionar");
		subpainel.add(btnsalvar, 8, 6);
		
		painel.setCenter(subpainel);
		generateBindings(txtId, txtDeData, txtEmData, txtIdClie, txtNmClie, txtIdLivro, txtValor);
	}
	
	public void generateBindings(TextField txtId, TextField txtEmData, TextField txtDeData, TextField txtIdClie, TextField txtNmClie, TextField txtIdLivro, TextField txtValor ) { 
		StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtEmData.textProperty(), control.emDataProperty(), localDtf);
//		Bindings.bindBidirectional(txtDeData.textProperty(), control.deDataProperty(), localDtf);
		Bindings.bindBidirectional(txtIdClie.textProperty(), control.idClieProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtNmClie.textProperty(), control.nmClieProperty());
		Bindings.bindBidirectional(txtIdLivro.textProperty(), control.idLivroProperty(), new NumberStringConverter()); // é para table do livro
		Bindings.bindBidirectional(txtValor.textProperty(), control.valorProperty(), new NumberStringConverter());
	
	}
	
	@Override
	public Pane renderizaPainel() {
		return painel;
	}

	@Override
	public TableView retornaTabela() {
		return table1;
	}

}
