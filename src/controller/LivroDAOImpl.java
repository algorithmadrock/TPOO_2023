package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;

import entity.Autor;
import entity.Editora;
import entity.Livro;
import java.util.ArrayList;
import java.sql.ResultSet;

public class LivroDAOImpl implements LivroDAO {

	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;
	
	public LivroDAOImpl() {

		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
	}

	@Override
	public void salvar(Livro l) {
		
		String sql = "INSERT INTO livro " + "(Id, Nome, idAutor, idEditora, Ano, Disponibilidade, genero, Valor) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, l.getId());
			stmt.setString(2, l.getNome());
			stmt.setObject(3, l.getAutor());
			stmt.setObject(4, l.getEditora());
			stmt.setDate(5, Date.valueOf(l.getAno()));
			stmt.setBoolean(6, l.getDisponibilidade());
			stmt.setString(7, l.getGenero());
			stmt.setFloat(8, l.getValor());
			stmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
	}
	
	
	private Autor recuperarAutorPorId(int autorId) {
	    Autor autor = null;

	    String sql = "SELECT * FROM autor WHERE Id = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	    	
	    	stmt.setInt(1, autorId);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            
	        	if (rs.next()) {
	                autor = new Autor();
	                autor.setId(rs.getInt("Id"));
	                autor.setNome(rs.getString("Nome"));
	                autor.setDtNascimento(rs.getDate("Nascimento").toLocalDate());
	                autor.setNacionalidade(rs.getString("Nacionalidade"));
	            
	        	}
	        }
	    
	    } catch (SQLException e) {
	        
	    	e.printStackTrace();
	    
	    }

	    return autor;
	}
	
	
	private Editora recuperarEditoraPorId(int editoraId) {
	    Editora editora = null;

	    String sql = "SELECT * FROM editora WHERE Id = ?";
	    
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	    	
	    	stmt.setInt(1, editoraId);
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            
	        	if (rs.next()) {
	                editora = new Editora();
	                editora.setId(rs.getInt("Id"));
	                editora.setNome(rs.getString("Nome"));
	                editora.setLocalizacao(rs.getString("Localizacao"));
	            
	        	}
	        }
	    } catch (SQLException e) {
	        
	    	e.printStackTrace();
	    
	    }

	    return editora;
	}
	

	@Override
	public List<Livro> lerTodos() {
		
		return pesquisarNome("");
		
	}

	@Override
	public List<Livro> pesquisarNome(String nome) {

		List<Livro> lista = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE Nome LIKE ?";
		
		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Livro l = new Livro();
				l.setId( rs.getInt("Id") );
				l.setNome( rs.getString("Nome") );
				
				
				int autorId = rs.getInt("idAutor");
				int editoraId = rs.getInt("idEditora");

				Autor autor = recuperarAutorPorId(autorId);
				Editora editora = recuperarEditoraPorId(editoraId);

				l.setAutor(autor);
				l.setEditora(editora);
				
				
				l.setAno( rs.getDate("Ano").toLocalDate());
				l.setDisponibilidade( rs.getBoolean("Disponibilidade") );
				l.setGenero( rs.getString("genero") );
				l.setValor( rs.getFloat("Valor") );
				lista.add(l);
				
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
			
		}

		return lista;
		
	}
}