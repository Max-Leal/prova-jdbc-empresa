package org.example.daos;

import org.example.models.Funcionario;
import org.example.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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

            stmt.setInt(1, funcionario.getId()); // id já existente da pessoa
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getDepartamento());

            stmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso!");

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Erro: já existe um funcionário com esse ID ou matrícula!");
            System.out.println("Log: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    // GET ALL
    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "select * from funcionario";

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("departamento")
                );
                funcionarios.add(f);
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
            WHERE p.id = ?
        """;
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("departamento")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    // UPDATE
    public void atualizar(int id, Funcionario funcionario) {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            String sqlPessoa = "UPDATE pessoa SET nome = ?, email = ? WHERE id = ?";
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, funcionario.getNome());
            stmtPessoa.setString(2, funcionario.getEmail());
            stmtPessoa.setInt(3, id);
            stmtPessoa.executeUpdate();

            String sqlFuncionario = "UPDATE funcionario SET matricula = ?, departamento = ? WHERE id = ?";
            PreparedStatement stmtFunc = conn.prepareStatement(sqlFuncionario);
            stmtFunc.setString(1, funcionario.getMatricula());
            stmtFunc.setString(2, funcionario.getDepartamento());
            stmtFunc.setInt(3, id);
            stmtFunc.executeUpdate();

            conn.commit();
            System.out.println("Funcionário atualizado com sucesso");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Erro: matrícula ou e-mail duplicado!");
            System.out.println("Log: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    // DELETE
    public void deletar(int id) {
        Connection conn = null;
        try {
            conn = Conexao.getConexao();
            conn.setAutoCommit(false);

            String sqlFuncionario = "DELETE FROM funcionario WHERE id = ?";
            PreparedStatement stmtFunc = conn.prepareStatement(sqlFuncionario);
            stmtFunc.setInt(1, id);
            stmtFunc.executeUpdate();

            String sqlPessoa = "DELETE FROM pessoa WHERE id = ?";
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setInt(1, id);
            stmtPessoa.executeUpdate();

            conn.commit();
            System.out.println("Funcionário deletado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.out.println("Erro ao fazer rollback: " + ex.getMessage());
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}
