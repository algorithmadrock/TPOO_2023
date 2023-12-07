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
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/biblioteca";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "alunofatec";
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
		String sql = "INSERT INTO funcionario " + "(Id, Nome, Nascimento, Cargo, Contratacao, Salario, Cpf) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getId());
			stmt.setString(2, f.getNome());
			stmt.setDate(3, Date.valueOf(f.getDtNascimento()));
			stmt.setString(4, f.getCargo());
			stmt.setDate(5, Date.valueOf(f.getDtContratacao()));
			stmt.setFloat(6, f.getSalario());
			stmt.setString(7, f.getCpf());
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
		String sql = "SELECT * FROM funcionario WHERE nome LIKE ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setId(rs.getInt("Id"));
				f.setNome(rs.getString("Nome"));
				f.setDtNascimento(rs.getDate("Nascimento").toLocalDate());
				f.setCargo(rs.getString("Cargo"));
				f.setDtContratacao(rs.getDate("Contratacao").toLocalDate());
				f.setSalario(rs.getFloat("Salario"));
				f.setCpf(rs.getString("Cpf"));
				lista.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}