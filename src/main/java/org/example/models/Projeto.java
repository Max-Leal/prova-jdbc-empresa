package org.example.models;

// Entidade projeto
public class Projeto {

	// atributos
	private int id;
	private String nome;
	private String descricao;
	private int id_funcionario;

	// construtores padrão, sem id e com id
	public Projeto() {

	}

	public Projeto(String nome, String descricao, int id_funcionario) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.id_funcionario = id_funcionario;
	}

	public Projeto(int id, String nome, String descricao, int id_funcionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.id_funcionario = id_funcionario;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	@Override
	public String toString() {
		return "Projeto: id = " + id + ", nome = " + nome + ", descricao = " + descricao
				+ ", id do funcionario responsável = " + id_funcionario;
	}

}