package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;

import entity.Livro;

import java.util.ArrayList;
import java.sql.ResultSet;

public class LivroDAOImpl implements LivroDAO {

	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/biblioteca";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "alunofatec";
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
		
		String sql = "INSERT INTO livro " + "(id, nome, autor, editora, ano, disponibilidade, genero, valor) VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)";

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

	@Override
	public List<Livro> lerTodos() {
		
		return pesquisarNome("");
		
	}

	@Override
	public List<Livro> pesquisarNome(String nome) {

		List<Livro> lista = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE nome LIKE ?";
		
		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Livro l = new Livro();
				l.setId( rs.getInt("id") );
				l.setNome( rs.getString("nome") );
				//l.setAutor( rs.getObject("autor") );
				//l.setEditora( rs.getObject("editora") );
				l.setAno( rs.getDate("ano").toLocalDate());
				l.setDisponibilidade( rs.getBoolean("disponibilidade") );
				l.setGenero( rs.getString("genero") );
				l.setValor( rs.getFloat("valor") );
				lista.add(l);
				
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
			
		}

		return lista;
		
	}
}