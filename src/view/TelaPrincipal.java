package view;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TelaPrincipal extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		MenuBar barraMenu = new MenuBar();
		Menu mAutor = new Menu("Autor");
		Menu mCategoria = new Menu("Categoria");
		Menu mCliente = new Menu("Cliente");
		Menu mDevolucao = new Menu("Devolucao");
		Menu mEditora = new Menu("Editora");
		Menu mFornecedor = new Menu("Fornecedor");
		Menu mLivro = new Menu("Livro");
		Menu mVenda = new Menu("Venda");
		MenuItem autor = new MenuItem("Autor");
		MenuItem categoria = new MenuItem("Categoria");
		MenuItem cliente = new MenuItem("Cliente");
		MenuItem devolucao = new MenuItem("Devolucao");
		MenuItem editora = new MenuItem("Editora");
		MenuItem fornecedor = new MenuItem("Fornecedor");
		MenuItem livro = new MenuItem("Livro");
		MenuItem venda = new MenuItem("Venda");
		mAutor.getItems().add(autor);
		mCategoria.getItems().add(categoria);
		mCliente.getItems().add(cliente);
		mDevolucao.getItems().add(devolucao);
		mEditora.getItems().add(editora);
		mFornecedor.getItems().add(fornecedor);
		mLivro.getItems().add(livro);
		mVenda.getItems().add(venda);
		
		
		barraMenu.getMenus().addAll(mAutor,mCategoria,mCliente,mDevolucao,mEditora,mFornecedor,mLivro,mVenda);
		
		BorderPane painel = new BorderPane();
		painel.setTop(barraMenu);
		Scene cena = new Scene(painel);
		stage.setScene(cena);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}