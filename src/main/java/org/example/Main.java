package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.daos.FuncionarioDao;
import org.example.daos.PessoaDao;
import org.example.daos.ProjetoDao;
import org.example.models.Funcionario;
import org.example.models.Pessoa;
import org.example.models.Projeto;

public class Main {
	public static void main(String[] args) {

		// Instância dos DAOs para manipulação das entidades
		PessoaDao pDao = new PessoaDao();
		FuncionarioDao fDao = new FuncionarioDao();
		ProjetoDao projetoDao = new ProjetoDao();

		Scanner sc = new Scanner(System.in);

		boolean continuar = true;
		// Loop principal
		while (continuar) {
			// Menu principal para escolha da entidade
			System.out.println("--------------------------------------");
			System.out.println(
					"Escolha a entidade a ser gerenciada:\n1 - Pessoa\n2 - Funcionário\n3 - Projeto\n4 - Sair");
			System.out.println("--------------------------------------");

			int resposta = sc.nextInt();
			sc.nextLine();

			// Validação da opção informada pelo usuário
			while (resposta < 1 || resposta > 4) {
				System.out.println("Por favor escolha entre uma das opções");
				resposta = sc.nextInt();
				sc.nextLine();
			}

			// Switch para as escolhas do usuario
			switch (resposta) {
			case 1: { // Gerenciamento da entidade Pessoa
				System.out.println("------------------------------------------");
				System.out.println(
						"Escolha a operação a ser feita com pessoa:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por id\n5 - Deletar");
				System.out.println("------------------------------------------");

				int respPessoa = sc.nextInt();
				sc.nextLine();

				// Validação da operação para pessoa
				while (respPessoa < 1 || respPessoa > 5) {
					System.out.println("Por favor escolha entre uma das opções");
					respPessoa = sc.nextInt();
					sc.nextLine();
				}

				// switch para gerenciamento de pessoa
				switch (respPessoa) {
				case 1: { // Listar todas as pessoas cadastradas no banco
					List<Pessoa> listPessoa = pDao.listarTodas();
					System.out.println("Lista de pessoas no banco: " + listPessoa.size());
					for (Pessoa pessoa : listPessoa) {
						System.out.println(pessoa);
					}
					break;
				}

				case 2: { // Inserir nova pessoa no banco
					System.out.print("Nome: ");
					String nome = sc.nextLine();

					System.out.print("Email: ");
					String email = sc.nextLine();

					// Cria objeto Pessoa e insere via DAO
					Pessoa p = new Pessoa(nome, email);
					pDao.inserir(p);
					break;
				}

				case 3: { // Atualizar dados da pessoa com ID informado
					System.out.print("ID da pessoa a ser alterada: ");
					int idAlterar = sc.nextInt();
					sc.nextLine();

					System.out.print("Novo nome: ");
					String nomeNovo = sc.nextLine();

					System.out.print("Novo email: ");
					String emailNovo = sc.nextLine();

					// Cria objeto atualizado e envia para DAO
					Pessoa pessoaAlterada = new Pessoa(nomeNovo, emailNovo);
					pDao.atualizar(idAlterar, pessoaAlterada);
					break;
				}

				case 4: { // Buscar pessoa pelo ID
					System.out.print("ID a ser buscado: ");
					int idBuscar = sc.nextInt();
					sc.nextLine();

					Pessoa pessoaBuscada = pDao.buscarPorId(idBuscar);

					// Verificação de existência
					if (pessoaBuscada == null) {
						System.out.println("Não existe pessoa com ID " + idBuscar);
					} else {
						System.out.println(pessoaBuscada);
					}
					break;
				}

				case 5: { // Deletar pessoa pelo ID
					System.out.print("ID da pessoa a ser deletada: ");
					int idDeletar = sc.nextInt();
					sc.nextLine();

					// Chamada DAO para exclusão
					pDao.deletar(idDeletar);
					break;
				}
				}
				break;
			}

			case 2: { // Gerenciamento da entidade Funcionário
				System.out.println("------------------------------------------");
				System.out.println(
						"Escolha a operação a ser feita com funcionário:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por ID\n5 - Remover vínculo de funcionário");
				System.out.println("------------------------------------------");

				int respFunc = sc.nextInt();
				sc.nextLine();

				// Validação da operação para funcionário
				while (respFunc < 1 || respFunc > 5) {
					System.out.println("Por favor escolha entre uma das opções");
					respFunc = sc.nextInt();
					sc.nextLine();
				}

				// switch para gerenciamento de funcionário
				switch (respFunc) {
				case 1: { // Listar todos os funcionários
					List<Funcionario> lista = fDao.listarTodos();
					System.out.println("Lista de funcionários: " + lista.size());
					for (Funcionario f : lista) {
						System.out.println(f);
					}
					break;
				}

				case 2: { // Inserir funcionário vinculado a pessoa existente
					System.out.print("ID da pessoa já cadastrada: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Matrícula (ex: F001): ");
					String matricula = sc.nextLine();

					System.out.print("Departamento: ");
					String departamento = sc.nextLine();

					// Verificação se pessoa com ID existe antes de inserir funcionário
					Pessoa p = pDao.buscarPorId(id);
					if (p == null) {
						System.out.println("Erro: não existe pessoa com esse ID. Cadastre a pessoa antes.");
					} else {
						// Cria objeto funcionário com dados válidos
						Funcionario f = new Funcionario(id, p.getNome(), p.getEmail(), matricula, departamento);
						fDao.inserir(f);
					}
					break;
				}

				case 3: { // Atualizar dados do funcionário
					System.out.print("ID do funcionário a ser atualizado: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Novo nome: ");
					String nome = sc.nextLine();

					System.out.print("Novo email: ");
					String email = sc.nextLine();

					System.out.print("Nova matrícula: ");
					String matricula = sc.nextLine();

					System.out.print("Novo departamento: ");
					String departamento = sc.nextLine();

					Funcionario f = new Funcionario(id, nome, email, matricula, departamento);
					fDao.atualizar(id, f);
					break;
				}

				case 4: { // Buscar funcionário por ID
					System.out.print("ID do funcionário a ser buscado: ");
					int id = sc.nextInt();
					sc.nextLine();

					Funcionario f = fDao.buscarPorId(id);
					if (f != null) {
						System.out.println(f);
					} else {
						System.out.println("Funcionário não encontrado.");
					}
					break;
				}

				case 5: { // Remover vínculo funcionário (excluir só da tabela funcionário)
					System.out.print("ID do funcionário a ser desvinculado: ");
					int id = sc.nextInt();
					sc.nextLine();

					// DAO responsável por remover vínculo mantendo a pessoa
					fDao.deletar(id);
					break;
				}
				}
				break;
			}

			case 3: { // Gerenciamento da entidade Projeto
				System.out.println("------------------------------------------");
				System.out.println(
						"Escolha a operação a ser feita com projeto:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por id\n5 - Deletar");
				System.out.println("------------------------------------------");
				int respProj = sc.nextInt();
				sc.nextLine();

				// Validação da operação para projeto
				while (respProj < 1 || respProj > 5) {
					System.out.println("Por favor escolha entre uma das opções");
					respProj = sc.nextInt();
					sc.nextLine();
				}

				// switch para gerenciamento de projeto
				switch (respProj) {

				case 1: { // Listar todos os projetos
					List<Projeto> lista = projetoDao.listarTodos();
					System.out.println("Projetos cadastrados: " + lista.size());
					for (Projeto p : lista) {
						System.out.println(p);
					}
					break;
				}

				case 2: { // Inserir novo projeto vinculado a funcionário
					System.out.print("Nome do projeto: ");
					String nome = sc.nextLine();
					System.out.print("Descrição: ");
					String desc = sc.nextLine();
					System.out.print("ID do funcionário responsável: ");
					int idFunc = sc.nextInt();
					sc.nextLine();

					// Cria projeto e tenta inserir
					Projeto novo = new Projeto(nome, desc, idFunc);
					projetoDao.inserir(novo);
					break;
				}

				case 3: { // Atualizar projeto existente
					System.out.print("ID do projeto a ser alterado: ");
					int idAlt = sc.nextInt();
					sc.nextLine();
					System.out.print("Novo nome: ");
					String nome = sc.nextLine();
					System.out.print("Nova descrição: ");
					String desc = sc.nextLine();
					System.out.print("Novo ID do funcionário responsável: ");
					int idFunc = sc.nextInt();
					sc.nextLine();

					Projeto atualizado = new Projeto(nome, desc, idFunc);
					projetoDao.atualizar(idAlt, atualizado);
					break;
				}

				case 4: { // Buscar projeto por ID
					System.out.print("ID do projeto a ser buscado: ");
					int idBusca = sc.nextInt();
					sc.nextLine();
					Projeto encontrado = projetoDao.buscarPorId(idBusca);

					if (encontrado == null) {
						System.out.println("Nenhum projeto encontrado com ID " + idBusca);
					} else {
						System.out.println(encontrado);
					}
					break;
				}

				case 5: { // Deletar projeto pelo ID
					System.out.print("ID do projeto a ser deletado: ");
					int idDel = sc.nextInt();
					sc.nextLine();
					projetoDao.deletar(idDel);
					break;
				}
				}
				break;
			}

			case 4: { // Encerrar execução do sistema
				continuar = false;
				System.out.println("Sistema encerrado.");
				break;
			}
			}
		}

		sc.close();
	}
}
