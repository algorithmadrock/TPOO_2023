package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
				stmt.setInt(1, v.getIdFunc());
				stmt.setInt(2, v.getIdClie());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Venda> pesquisarNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
