package org.example.models;

// Classe Funcionario herdado de Pessoa
public class Funcionario extends Pessoa {

	// atributos
	private String matricula;
	private String departamento;

	// construtores padrão, sem id e com id
	public Funcionario() {

	}

	public Funcionario(String nome, String email, String matricula, String departamento) {
		super(nome, email);
		this.matricula = matricula;
		this.departamento = departamento;
	}

	public Funcionario(int id, String nome, String email, String matricula, String departamento) {
		super(id, nome, email);
		this.matricula = matricula;
		this.departamento = departamento;
	}

	// getters e setters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Funcionario id = " + super.getId() + ", nome = " + super.getNome() + ", email = " + super.getEmail()
				+ ", matricula = " + matricula + ", departamento = " + departamento;
	}

}
