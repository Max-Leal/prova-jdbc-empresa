package org.example.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.models.Projeto;
import org.example.utils.Conexao;

public class ProjetoDao {

	// CREATE
	public void inserir(Projeto projeto) {
		String verificarFuncionario = "SELECT id FROM funcionario WHERE id = ?";
		String sql = "INSERT INTO projeto (nome, descricao, id_funcionario) VALUES (?, ?, ?)";

		try (Connection conn = Conexao.getConexao()) {
			// Verificar se funcionário existe
			PreparedStatement stmtVerif = conn.prepareStatement(verificarFuncionario);
			stmtVerif.setInt(1, projeto.getId_funcionario());
			ResultSet rs = stmtVerif.executeQuery();

			if (!rs.next()) {
				System.out.println("Erro: não existe funcionário com ID " + projeto.getId_funcionario());
				return;
			}

			// Inserir projeto
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, projeto.getNome());
			stmt.setString(2, projeto.getDescricao());
			stmt.setInt(3, projeto.getId_funcionario());
			stmt.executeUpdate();

			System.out.println("Projeto inserido com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir projeto: " + e.getMessage());
		}
	}

	// LISTAR 
	public List<Projeto> listarTodos() {
		List<Projeto> projetos = new ArrayList<>();
		String sql = "SELECT * FROM projeto";

		try (Connection conn = Conexao.getConexao();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Projeto p = new Projeto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
						rs.getInt("id_funcionario"));
				projetos.add(p);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar projetos: " + e.getMessage());
		}

		return projetos;
	}

	// BUSCAR POR ID
	public Projeto buscarPorId(int id) {
		String sql = "SELECT * FROM projeto WHERE id = ?";
		try (Connection conn = Conexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Projeto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"),
						rs.getInt("id_funcionario"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar projeto: " + e.getMessage());
		}

		return null;
	}

	// UPDATE
	public void atualizar(int id, Projeto projeto) {
	    String sql = "UPDATE projeto SET nome = ?, descricao = ?, id_funcionario = ? WHERE id = ?";
	    try (Connection conn = Conexao.getConexao()) {

	        // Verifica se o funcionário existe
	        String sqlCheck = "SELECT id FROM funcionario WHERE id = ?";
	        try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
	            stmtCheck.setInt(1, projeto.getId_funcionario());
	            ResultSet rs = stmtCheck.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Erro: funcionário com ID " + projeto.getId_funcionario() + " não existe!");
	                return;
	            }
	        }

	        // Atualiza o projeto
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, projeto.getNome());
	            stmt.setString(2, projeto.getDescricao());
	            stmt.setInt(3, projeto.getId_funcionario());
	            stmt.setInt(4, id);

	            int rows = stmt.executeUpdate();
	            if (rows > 0) {
	                System.out.println("Projeto atualizado com sucesso.");
	            } else {
	                System.out.println("Nenhum projeto encontrado com ID " + id + ".");
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar projeto: " + e.getMessage());
	    }
	}

	// DELETE
	public void deletar(int id) {
		String sql = "DELETE FROM projeto WHERE id = ?";

		try (Connection conn = Conexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			int rows = stmt.executeUpdate();

			if (rows > 0) {
				System.out.println("Projeto deletado com sucesso!");
			} else {
				System.out.println("Nenhum projeto encontrado com ID " + id);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao deletar projeto: " + e.getMessage());
		}
	}

	// EXTRA: contar projetos de um funcionário (útil para FuncionarioDao contar se há algum projeto relacionado ao funcionario antes de deletar)
	public int contarProjetosPorFuncionario(int idFuncionario) {
		String sql = "SELECT COUNT(*) AS total FROM projeto WHERE id_funcionario = ?";
		try (Connection conn = Conexao.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idFuncionario);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("total");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao contar projetos: " + e.getMessage());
		}
		return 0;
	}
}
