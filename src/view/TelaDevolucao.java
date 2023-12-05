package view;

import java.time.format.DateTimeFormatter;

import controller.ControlDevolucao;
import controller.ControlEmprestimo;
import entity.Devolucao;
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
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class TelaDevolucao implements TelaMudar{
	
	private BorderPane painel;
	private ControlDevolucao control;
	private TableView<Devolucao> table;
	private DateTimeFormatter dtf;
	
	public TelaDevolucao() {
		control = new ControlDevolucao();
		table = new TableView<>();
		dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		painel = new BorderPane();
		
		centertela();
		bottomtela();
	}
	
	public void bottomtela() {
		table.setItems(control.getLista());
		
		TableColumn<Devolucao, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Devolucao, Integer>("Id") );
		
		TableColumn<Devolucao, String> colData = new TableColumn<>("Data");
		colData.setCellValueFactory( itemData ->	new ReadOnlyStringWrapper( dtf.format(itemData.getValue().getData()) ));	
		
		TableColumn<Devolucao, Float> colMulta = new TableColumn<>("Multa");
		colMulta.setCellValueFactory(new PropertyValueFactory<Devolucao, Integer>("Multa") );
		
		TableColumn<Devolucao, Boolean> colStatus = new TableColumn<>("Status");
		colStatus.setCellValueFactory(new PropertyValueFactory<Emprestimo, Boolean>("Status"));
		
 
        table.getColumns().addAll(colId, colData, colMulta, colStatus);
	}

	public void centertela() {
		
		GridPane subpainel = new GridPane();
		subpainel.setRowSpan(subpainel, 6);
		subpainel.setColumnSpan(subpainel, 6);
		
		//ELEMENTOS DO CENÁRIO
		Label lblId = new Label("ID # "); 
		subpainel.add(lblId, 2, 1);
		TextField txtId = new TextField();
		subpainel.add(txtId, 3, 1);
		txtId.setEditable(false);
		
		Label lblData = new Label("DATA "); 
		subpainel.add(lblData, 2, 2);
		TextField txtData = new TextField();
		subpainel.add(txtData, 3, 2);
		
		Label lblStatus = new Label("STATUS"); 
		subpainel.add(lblStatus, 7, 5);
		TextField txtStatus = new TextField();
		subpainel.add(txtStatus, 8, 6);
		txtStatus.setEditable(false);
		
		Label lblMulta = new Label("MULTA "); 
		subpainel.add(lblMulta, 5, 4);
		TextField txtMulta = new TextField();
		subpainel.add(txtMulta, 6, 3);
		
		Button btnsalvar = new Button("Adicionar");
		subpainel.add(btnsalvar, 10, 8);
		
		painel.setCenter(subpainel);
		generateBindings(txtId, txtData, txtStatus, txtMulta);
	}
	
	public void generateBindings(TextField txtId, TextField txtData, TextField txtMulta, TextField txtStatus ) { 
		StringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		
		Bindings.bindBidirectional(txtId.textProperty(), control.idProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtData.textProperty(), control.dataProperty(), localDtf);
		Bindings.bindBidirectional(txtMulta.textProperty(), control.multaProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(txtStatus.textProperty(), control.statusProperty(), new BooleanStringConverter());
	}
	
	@Override
	
	public Pane renderizaPainel() {
		// TODO Auto-generated method stub
		return painel;
	}

	@Override
	public TableView retornaTabela() {
		// TODO Auto-generated method stub
		return table;
	}

}
