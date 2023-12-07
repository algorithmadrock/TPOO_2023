package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mariadb.jdbc.Connection;

import entity.Fornecedor;
import javafx.collections.FXCollections;

public class FornecedorDAOImpl implements FornecedorDAO {
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;

	public FornecedorDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Fornecedor f) {
		String sql = "INSERT INTO fornecedor " + "(Id, cnpj, nome, status) VALUES " + "(?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getId());
			stmt.setString(2, f.getCnpj());
			stmt.setString(3, f.getNome());
			stmt.setBoolean(4, f.isStatus());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Fornecedor> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public List<Fornecedor> pesquisarNome(String nome) {
		// TODO Auto-generated method stub
		List<Fornecedor> lista = FXCollections.observableArrayList();
		String sql = "SELECT * FROM fornecedor WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(rs.getInt("Id"));
				f.setCnpj(rs.getString("cnpj"));
				f.setNome(rs.getString("nome"));
				f.setStatus(rs.getBoolean("status"));
				lista.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
}
