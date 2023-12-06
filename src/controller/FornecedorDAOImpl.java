package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.mariadb.jdbc.Connection;

import entity.Fornecedor;

public class FornecedorDAOImpl implements FornecedorDAO {
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/biblioteca";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "alunofatec";
	private Connection con;

	public FornecedorDAOImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
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
	public Set<Fornecedor> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public Set<Fornecedor> pesquisarNome(String nome) {
		// TODO Auto-generated method stub
		Set<Fornecedor> lista = new HashSet<>();
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
