package controller;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.mariadb.jdbc.Connection;

import entity.Funcionario;

public class FuncionarioDAOImpl implements FuncionarioDAO {
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/Livraria";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";
	private Connection con;

	public FuncionarioDAOImpl() {
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
	public void salvar(Funcionario f) {
		String sql = "INSERT INTO funcionario "
				+ "(nome, Nascimento, cargo, Contratacao, salario, cpf) VALUES " + "(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, f.getNome());
			stmt.setDate(2, Date.valueOf(f.getDtNascimento()));
			stmt.setString(3, f.getCargo());
			stmt.setDate(4, Date.valueOf(f.getDtContratacao()));
			stmt.setFloat(5, f.getSalario());
			stmt.setString(6, f.getCpf());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Set<Funcionario> lerTodos() {
		return pesquisarNome("");
	}

	@Override
	public Set<Funcionario> pesquisarNome(String nome) {
		// TODO Auto-generated method stub
		Set<Funcionario> lista = new HashSet<>();
		String sql = "SELECT * FROM funcionarios WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setId(rs.getInt("id"));
				f.setNome(rs.getString("nome"));
				f.setDtNascimento(rs.getDate("nascimento").toLocalDate());
				f.setCargo(rs.getString("cargo"));
				f.setDtContratacao(rs.getDate("contratacao").toLocalDate());
				f.setSalario(rs.getFloat("salario"));
				f.setCpf(rs.getString("cpf"));
				lista.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	public Funcionario pesquisarId(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM funcionario WHERE Id = ?";
		Funcionario f = new Funcionario();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id );
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				f.setId(rs.getInt("id"));
				f.setNome(rs.getString("nome"));
				f.setDtNascimento(rs.getDate("nascimento").toLocalDate());
				f.setCargo(rs.getString("cargo"));
				f.setDtContratacao(rs.getDate("contratacao").toLocalDate());
				f.setSalario(rs.getFloat("salario"));
				f.setCpf(rs.getString("cpf"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

}