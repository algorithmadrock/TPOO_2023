package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/biblioteca";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "alunofatec";
	private Connection con;

	public CategoriaDAOImpl() {
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
	public void salvar(Categoria c) {
		String sql = "INSERT INTO categoria" + "(Id, Nome, Descricao)" + "(?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getId());
			stmt.setString(2, c.getNome());
			stmt.setString(3, c.getDescricao());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Categoria> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public List<Categoria> pesquisarNome(String nome) {
		List<Categoria> lista = new ArrayList<>();
		String sql = "SELECT * FROM categoria WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Categoria c = new Categoria();
				c.setId(rs.getInt("Id"));
				c.setNome(rs.getString("Nome"));
				c.setDescricao(rs.getString("Descricao"));
				lista.add(c);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}