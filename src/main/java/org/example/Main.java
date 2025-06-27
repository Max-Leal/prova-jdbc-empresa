package org.example;

import org.example.daos.PessoaDao;
import org.example.models.Pessoa;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static <PessoaDAO> void main(String[] args) {

        // Declarando os daos
        PessoaDao pDao = new PessoaDao();

        //Scanner
        Scanner sc = new Scanner(System.in);

        // Buscar a lista de pessoas no banco banco
        List<Pessoa> listPessoa = pDao.listarTodas();

        System.out.println("Lista de pessoas no banco: " + listPessoa.size());
        for (Pessoa pessoa : listPessoa) {
            System.out.println(pessoa);
        }


		// Comando de inserir pessoa
		//pDao.inserir(new Pessoa("Jonelson","Jonel@gmail.com"));

		// Comando de deletar por id
		//pDao.deletar(2);

		// Comando de buscar por id
		//Pessoa pessoaBuscada = pDao.buscarPorId(1);

		// Comando de update
		//Pessoa pessoaAlterada = new Pessoa("Leonardo","Leo@gmail.com");
		//pDao.atualizar(1, pessoaAlterada);

    }
}