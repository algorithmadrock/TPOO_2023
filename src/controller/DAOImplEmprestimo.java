package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.Emprestimo;
import entity.Fornecedor;

public class DAOImplEmprestimo implements DAOEmprestimo{
	//ALTERAR DADOS DO BANCO DE DADOS
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;
	
	public DAOImplEmprestimo() {
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
	public void salvar(Emprestimo emp) {
		String sql = "INSERT INTO emprestimo " + "(id, emprestimo, devolucao, idClie, nmClie, valor) VALUES " + "(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getId());
			stmt.setDate(2, Date.valueOf(emp.getEmprestimo()));
			stmt.setDate(3, Date.valueOf(emp.getDevolucao()));
			stmt.setInt(4, emp.getIdClie());
			stmt.setString(5, emp.getNmClie());
			stmt.setFloat(6, emp.getValor());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Emprestimo> lerTodos() {
		return pesquisarId(0);
	}

	@Override
	public List<Emprestimo> pesquisarId(int id) {
	// TODO Auto-generated method stub
		List<Emprestimo> lista = new ArrayList<>();
		String sql = "SELECT * FROM emprestimo WHERE id LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + id + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Emprestimo emp = new Emprestimo();
				emp.setId(rs.getInt("Id"));
				emp.setEmprestimo(rs.getDate("Emprestimo").toLocalDate());
				emp.setDevolucao(rs.getDate("Devolucao").toLocalDate());
				emp.setIdClie(rs.getInt("Id Cliente"));
				emp.setNmClie(rs.getString("Nome Cliente"));
				emp.setValor(rs.getFloat("Valor"));
				lista.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lista;
	}
}