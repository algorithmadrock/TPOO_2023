package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autor;

public class AutorDAOImpl implements AutorDAO {
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;

	public AutorDAOImpl() {
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
	public void salvar(Autor a) {
		String sql = "INSERT INTO autor" + "(Id, Nome, Nascimento, Nacionalidade) Values" + "(?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, a.getId());
			stmt.setString(2, a.getNome());
			stmt.setDate(3, Date.valueOf(a.getDtNascimento()));
			stmt.setString(4, a.getNacionalidade());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Autor> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public List<Autor> pesquisarNome(String nome) {
		List<Autor> lista = new ArrayList<>();
		String sql = "SELECT * FROM autor WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Autor a = new Autor();
				a.setId(rs.getInt("Id"));
				a.setNome(rs.getString("Nome"));
				a.setDtNascimento(rs.getDate("Nascimento").toLocalDate());
				a.setNacionalidade(rs.getString("Nacionaliade"));
				lista.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}