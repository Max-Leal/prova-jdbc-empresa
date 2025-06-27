package org.example.models;


public class Funcionario extends Pessoa {

    private String matricula;
    private String departamento;

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
        return "Pessoa [id=" + super.getId() + ", nome=" + super.getNome() + ", email=" + super.getEmail()
                + "] Funcionario [matricula=" + matricula + ", departamento=" + departamento + "]";
    }

}

