package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Devolucao;

public class DAOImplDevolucao implements DAODevolucao {
	// ALTERAR DADOS DO BANCO DE DADOS
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;

	public DAOImplDevolucao() {
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
	public void salvar(Devolucao dev) {
		String sql = "INSERT INTO devolucao(id, data, multa, status) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, dev.getId());
			stmt.setDate(2, Date.valueOf(dev.getData()));
			stmt.setFloat(3, dev.getMulta());
			stmt.setBoolean(4, dev.isStatus());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Devolucao> lerTodos() {
		return pesquisarId(0);
	}

	@Override
	public List<Devolucao> pesquisarId(int id) {
		// TODO Auto-generated method stub
		List<Devolucao> lista = new ArrayList<>();
		String sql = "SELECT * FROM devolucao WHERE id LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + id + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Devolucao dev = new Devolucao();
				dev.setId(rs.getInt("Id"));
				dev.setData(rs.getDate("Data").toLocalDate());
				dev.setMulta(rs.getFloat("Multa"));
				dev.setStatus(rs.getBoolean("Status"));
				lista.add(dev);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
}
