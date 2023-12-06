package view;

import java.time.format.DateTimeFormatter;

import controller.ControllerCliente;
import entity.Cliente;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.NumberStringConverter;

public class TelaCliente implements TelaMudar{
	public TelaCliente() {
		criaPainel();
		criaBindings();
		criarTabela();
	}

	private GridPane painel = new GridPane();
	private TextField txtId = new TextField("ID");
	private TextField txtNome = new TextField("Nome");
	private TextField txtNascimento = new TextField("Data de nascimento");
	private TextField txtCPF = new TextField("CPF");
	private TextField txtEndereco= new TextField("Endereco");
	private TextField txtTelefone = new TextField("Telefone");
	private TextField txtEmail = new TextField("Email");
	private TableView<Cliente> tabela = new TableView<>();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Button btnSalvar = new Button("Salvar");
	private Button btnPesquisar = new Button("Pesquisar");
	private ControllerCliente cc = new ControllerCliente();
	
	
	private void criaPainel() {
		painel.add(new Label("ID: "), 0, 0);
		painel.add(txtId, 1, 0);
		painel.add(new Label("Nome: "), 0, 1);
		painel.add(txtNome, 1, 1);
		painel.add(new Label("Data de Nascimento: "), 0, 2);
		painel.add(txtNascimento, 1, 2);
		painel.add(new Label("CPF: "), 0, 3);
		painel.add(txtCPF, 1, 3);
		painel.add(new Label("Telefone: "), 0, 4);
		painel.add(txtTelefone, 1, 4);
		painel.add(new Label("E-mail: "), 0, 5);
		painel.add(txtEmail, 1, 5);
		painel.add(new Label("Endereco: "), 0, 6);
		painel.add(txtEndereco, 1, 6);
		painel.add(btnSalvar, 0, 7);
		painel.add(btnPesquisar, 1, 7);
				
	}
	
	private void criaBindings() {
		Bindings.bindBidirectional(txtId.textProperty(), cc.idProperty(), new NumberStringConverter() );
		Bindings.bindBidirectional(txtNome.textProperty(), cc.nomeProperty());
		LocalDateStringConverter localDtf = new LocalDateStringConverter(dtf, dtf);
		Bindings.bindBidirectional(txtNascimento.textProperty(), cc.nascimentoProperty(),
						localDtf);
		Bindings.bindBidirectional(txtCPF.textProperty(), cc.cpfProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), cc.telefoneProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), cc.emailProperty());
		Bindings.bindBidirectional(txtEndereco.textProperty(), cc.enderecoProperty());
		
		
	}
	
	private void criarTabela() {
		
		tabela.setItems(cc.getLista());
		
		TableColumn<Cliente, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(
				new PropertyValueFactory<Cliente, Integer>("ID"));
		
		TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		
		TableColumn<Cliente, String> colNascimento = new TableColumn<>("Nascimento");
		colNascimento.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(
						dtf.format(itemData.getValue().getDtNascimento())));
		
		TableColumn<Cliente, String> colCPF = new TableColumn<>("CPF");
		colCPF.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCpf()));
		
		TableColumn<Cliente, String> colTel = new TableColumn<>("Telefone");
		colTel.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTelefone()));
		
		TableColumn<Cliente, String> colEmail = new TableColumn<>("E-mail");
		colTel.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEmail()));
		
		TableColumn<Cliente, String> colEnd = new TableColumn<>("Endereço");
		colTel.setCellValueFactory(
				itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEndereco()));
		
		tabela.getColumns().addAll(colId,colNome, colNascimento, colCPF, colTel, colEmail, colEnd);
	}
	
	@Override
	public Pane renderizaPainel() {
		return painel;
	}

	@Override
	public TableView retornaTabela() {
		return tabela;
	}

}
