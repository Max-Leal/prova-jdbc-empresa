package org.example.daos;

import org.example.models.Funcionario;
import org.example.utils.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

	// CREATE
	public void inserir(Funcionario funcionario) {
		String sql = "INSERT INTO funcionario (id, matricula, departamento) VALUES (?, ?, ?)";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, funcionario.getId()); // ID da pessoa já existente
			stmt.setString(2, funcionario.getMatricula());
			stmt.setString(3, funcionario.getDepartamento());

			stmt.executeUpdate();
			System.out.println("Funcionário inserido com sucesso!");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("------------------------------------------------------------");
			System.out.println("Erro: ID de pessoa inexistente ou matrícula duplicada!");
			System.out.println("Log: " + e.getMessage());
			System.out.println("------------------------------------------------------------");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir funcionário: " + e.getMessage());
		}
	}

	// GET ALL
	public List<Funcionario> listarTodos() {
		List<Funcionario> funcionarios = new ArrayList<>();
		String sql = """
				SELECT p.id, p.nome, p.email, f.matricula, f.departamento
				FROM funcionario f
				JOIN pessoa p ON f.id = p.id
				""";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("matricula"), rs.getString("departamento"));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar funcionários: " + e.getMessage());
		}
		return funcionarios;
	}

	// GET ONE
	public Funcionario buscarPorId(int id) {
		String sql = """
				SELECT p.id, p.nome, p.email, f.matricula, f.departamento
				FROM funcionario f
				JOIN pessoa p ON f.id = p.id
				WHERE f.id = ?
				""";
		try {
			Connection conn = Conexao.getConexao();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
						rs.getString("matricula"), rs.getString("departamento"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar funcionário por ID: " + e.getMessage());
		}
		return null;
	}

	// UPDATE
	public void atualizar(int id, Funcionario funcionario) {
		String sqlPessoa = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
		String sqlFuncionario = "UPDATE funcionario SET matricula = ?, departamento = ? WHERE id = ?";

		try {
			Connection conn = Conexao.getConexao();

			// Atualiza tabela pessoa
			PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
			stmtPessoa.setString(1, funcionario.getNome());
			stmtPessoa.setString(2, funcionario.getEmail());
			stmtPessoa.setInt(3, id);
			stmtPessoa.executeUpdate();

			// Atualiza tabela funcionario
			PreparedStatement stmtFunc = conn.prepareStatement(sqlFuncionario);
			stmtFunc.setString(1, funcionario.getMatricula());
			stmtFunc.setString(2, funcionario.getDepartamento());
			stmtFunc.setInt(3, id);
			stmtFunc.executeUpdate();

			System.out.println("Funcionário atualizado com sucesso!");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Erro: matrícula ou e-mail duplicado!");
			System.out.println("Log: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
		}
	}

	// DELETE (remover somente da tabela funcionario, manter na tabela pessoa)
	public void deletar(int id) {
		Connection conn = null;

		try {
			// Verifica se o funcionário está vinculado a algum projeto
			ProjetoDao projetoDao = new ProjetoDao();
			int totalProjetos = projetoDao.contarProjetosPorFuncionario(id);

			if (totalProjetos > 0) {
				System.out.println("Erro: este funcionário está vinculado a " + totalProjetos
						+ " projeto(s) e não pode ser deletado.");
				return;
			}

			conn = Conexao.getConexao();
			conn.setAutoCommit(false);

			// Remove da tabela de funcionario, mas mantém em pessoa
			String sqlFuncionario = "DELETE FROM funcionario WHERE id = ?";
			PreparedStatement stmtFunc = conn.prepareStatement(sqlFuncionario);
			stmtFunc.setInt(1, id);
			int rows = stmtFunc.executeUpdate();

			if (rows > 0) {
				conn.commit();
				System.out.println("Funcionário removido com sucesso (pessoa mantida).");
			} else {
				System.out.println("Funcionário não encontrado com ID " + id);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao deletar funcionário: " + e.getMessage());
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException ex) {
				System.out.println("Erro ao fazer rollback: " + ex.getMessage());
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexão: " + e.getMessage());
			}
		}
	}
}
