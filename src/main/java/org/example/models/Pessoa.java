package org.example.models;

// Entidade Pessoa
public class Pessoa {

	// atributos
	private int id;
	private String nome;
	private String email;

	// construtores padrão, sem id e com id
	public Pessoa() {

	}

	public Pessoa(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public Pessoa(int id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	// getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Pessoa: id = " + id + ", nome = " + nome + ", email = " + email;
	}

}
