package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaPrincipal extends Application {

	private TelaMudar telaAtual = new TelaAutor();;
	BorderPane painel = new BorderPane();

	@Override
	public void start(Stage stage) throws Exception {
		MenuBar barraMenu = new MenuBar();
		Menu mAutor = new Menu("Autor");
		Menu mCategoria = new Menu("Categoria");
		Menu mCliente = new Menu("Cliente");
		Menu mDevolucao = new Menu("Devolucao");
		Menu mEditora = new Menu("Editora");
		Menu mEmprestimo = new Menu("Emprestimo");
		Menu mFornecedor = new Menu("Fornecedor");
		Menu mFuncionario = new Menu("Funcionario");
		Menu mLivro = new Menu("Livro");
		Menu mVenda = new Menu("Venda");
		MenuItem autor = new MenuItem("Autor");
		MenuItem categoria = new MenuItem("Categoria");
		MenuItem cliente = new MenuItem("Cliente");
		MenuItem devolucao = new MenuItem("Devolucao");
		MenuItem editora = new MenuItem("Editora");
		MenuItem emprestimo = new MenuItem("Emprestimo");
		MenuItem fornecedor = new MenuItem("Fornecedor");
		MenuItem funcionario = new MenuItem("Funcionario");
		MenuItem livro = new MenuItem("Livro");
		MenuItem venda = new MenuItem("Venda");
		mAutor.getItems().add(autor);
		mCategoria.getItems().add(categoria);
		mCliente.getItems().add(cliente);
		mDevolucao.getItems().add(devolucao);
		mEditora.getItems().add(editora);
		mEmprestimo.getItems().add(emprestimo);
		mFornecedor.getItems().add(fornecedor);
		mFuncionario.getItems().add(funcionario);
		mLivro.getItems().add(livro);
		mVenda.getItems().add(venda);

		barraMenu.getMenus().addAll(mAutor, mCategoria, mCliente, mDevolucao, mEditora, mEmprestimo, mFornecedor,
				mFuncionario, mLivro, mVenda);
		
		autor.setOnAction(ct -> {telaAtual=new TelaAutor();painel.setCenter(telaAtual.renderizaPainel());});
		categoria.setOnAction(ct -> {telaAtual=new TelaCategoria();painel.setCenter(telaAtual.renderizaPainel());});
		cliente.setOnAction(ct -> {telaAtual=new TelaCliente();painel.setCenter(telaAtual.renderizaPainel());});
		devolucao.setOnAction(ct -> {telaAtual=new TelaDevolucao();painel.setCenter(telaAtual.renderizaPainel());});
		editora.setOnAction(ct -> {telaAtual=new TelaEditora();painel.setCenter(telaAtual.renderizaPainel());});
		emprestimo.setOnAction(ct -> {telaAtual=new TelaEmprestimo();painel.setCenter(telaAtual.renderizaPainel());});
		fornecedor.setOnAction(ct -> {telaAtual=new TelaFornecedor();painel.setCenter(telaAtual.renderizaPainel());});
		funcionario.setOnAction(ct -> {telaAtual=new TelaFuncionario();painel.setCenter(telaAtual.renderizaPainel());});
		livro.setOnAction(ct -> {telaAtual=new TelaLivro();painel.setCenter(telaAtual.renderizaPainel());});
		venda.setOnAction(ct -> {telaAtual=new TelaVenda();painel.setCenter(telaAtual.renderizaPainel());});
		
		painel.setTop(barraMenu);
		Scene cena = new Scene(painel);
		stage.setScene(cena);
		stage.show();
		painel.setCenter(telaAtual.renderizaPainel());
		stage.setHeight(500);
		stage.setWidth(700);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
