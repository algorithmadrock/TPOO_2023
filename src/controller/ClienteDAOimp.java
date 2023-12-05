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

public class ClienteDAOimp implements ClienteDAO{
	private static final String JDBC_URL =
			"jdbc:mariadb://localhost:3306/escola?characterEncoding=latin1";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "Clientefatec";
	private Connection con;
	public ClienteDAOimp() { 
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
	public void salvar(Cliente c) {
		String sql = "INSERT INTO Clientes "
			+ "(id, nome, nascimento, telefone, email, endereco) VALUES "
			+ "(?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, c.getId());
			stmt.setString(2, c.getNome());
			stmt.setDate(3, Date.valueOf(c.getDataNascimento()));
			stmt.setString(4, c.getCpf());
			stmt.setString(5, c.getTelefone());
			stmt.setString(6, c.getEmail());
			stmt.setString(7, c.getEndereco());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public List<Cliente> pesquisarNome(String nome) {
		// TODO Auto-generated method stub
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM Clientes WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Cliente c = new Cliente();
				c.setId( rs.getInt("id") );
				c.setNome( rs.getString("nome") );
				c.setDataNascimento( rs.getDate("nascimento").toLocalDate() );
				c.setCpf(rs.getString("cpf"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				c.setEndereco(rs.getString("endereco"));
				lista.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
}
