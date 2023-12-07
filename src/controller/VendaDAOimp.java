package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cliente;
import entity.Funcionario;
import entity.Venda;

public class VendaDAOimp implements VendaDAO{
	
	private static final String JDBC_URL =
			"jdbc:mariadb://localhost:3306/livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;
	public VendaDAOimp() { 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(
					JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvarVenda(Venda v) {
		String sql = "INSERT INTO venda "
				+ "(idFuncionario, idCliente, Data, Valor) VALUES "
				+ "(?, ?, ?, ?)";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, v.getFunc());
				stmt.setInt(2, v.getClie());
				stmt.setDate(3, Date.valueOf(v.getData()));
				stmt.setFloat(4, v.getValor());
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public List<Venda> lerTodos() {
		List<Venda> lista = new ArrayList<>();
		String sql = "SELECT * FROM venda";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Venda v = new Venda();
				v.setId( rs.getInt("id") );
				Funcionario f = new Funcionario();
				f.setId(rs.getInt("idFuncionario"));
				v.setFunc(f);
				Cliente c = new Cliente();
				c.setId(rs.getInt("idCliente"));
				v.setClie(c);
				v.setData(rs.getDate("data").toLocalDate());
				v.setValor(rs.getFloat("valor"));
				lista.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Venda> pesquisarId(int id) {
		// TODO Auto-generated method stub
		List<Venda> lista = new ArrayList<>();
		String sql = "SELECT * FROM venda WHERE Id LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + id + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Venda v = new Venda();
				v.setId( rs.getInt("id") );
				Funcionario f = new Funcionario();
				f.setId(rs.getInt("idFuncionario"));
				v.setFunc(f);
				Cliente c = new Cliente();
				c.setId(rs.getInt("idCliente"));
				v.setClie(c);
				lista.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	

}
