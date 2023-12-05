package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.List;

import entity.Editora;

import java.util.ArrayList;
import java.sql.ResultSet;

public class EditoraDAOImpl implements EditoraDAO{
	
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/escola?characterEncoding=latin1";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "alunofatec";
	private Connection con;
	public EditoraDAOImpl() {

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
	public void salvar(Editora ed) {
		
		String sql = "INSERT INTO livros " + "(id, nome, localizacao) VALUES " + "(?, ?, ?)";

		try {
			
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, ed.getId());
			stmt.setString(2, ed.getNome());
			stmt.setObject(3, ed.getLocalizacao());
			stmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		}
		
	}

	@Override
	public List<Editora> lerTodos() {
		
		return pesquisarNome("");
		
	}

	@Override
	public List<Editora> pesquisarNome(String nome) {

		List<Editora> lista = new ArrayList<>();
		String sql = "SELECT * FROM editora WHERE nome LIKE ?";
		
		try {

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				Editora ed = new Editora();
				ed.setId( rs.getInt("id") );
				ed.setNome( rs.getString("nome") );
				ed.setLocalizacao( rs.getString("autor") );
				lista.add(ed);
				
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
			
		}

		return lista;
		
	}
}