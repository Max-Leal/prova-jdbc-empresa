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

		// DAO
		PessoaDao pDao = new PessoaDao();
		FuncionarioDao fDao = new FuncionarioDao();
		ProjetoDao projetoDao = new ProjetoDao();

		// Scanner
		Scanner sc = new Scanner(System.in);

		boolean continuar = true;
		while (continuar) {
			System.out.println("--------------------------------------");
			System.out.println(
					"Escolha a entidade a ser gerenciada:\n1 - Pessoa\n2 - Funcionário\n3 - Projeto\n4 - Sair");
			System.out.println("--------------------------------------");

			int resposta = sc.nextInt();
			sc.nextLine();

			while (resposta < 1 || resposta > 4) {
				System.out.println("Por favor escolha entre uma das opções");
				resposta = sc.nextInt();
				sc.nextLine();
			}

			switch (resposta) {
			case 1: {
				System.out.println("------------------------------------------");
				System.out.println(
						"Escolha a operação a ser feita com pessoa:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por id\n5 - Deletar");
				System.out.println("------------------------------------------");

				int respPessoa = sc.nextInt();
				sc.nextLine();

				while (respPessoa < 1 || respPessoa > 5) {
					System.out.println("Por favor escolha entre uma das opções");
					respPessoa = sc.nextInt();
					sc.nextLine();
				}

				switch (respPessoa) {
				// LISTAR
				case 1: {
					List<Pessoa> listPessoa = pDao.listarTodas();
					System.out.println("Lista de pessoas no banco: " + listPessoa.size());
					for (Pessoa pessoa : listPessoa) {
						System.out.println(pessoa);
					}
					break;
				}

				// INSERIR
				case 2: {
					System.out.print("Nome: ");
					String nome = sc.nextLine();

					System.out.print("Email: ");
					String email = sc.nextLine();

					Pessoa p = new Pessoa(nome, email);
					pDao.inserir(p);
					break;
				}

				// ATUALIZAR
				case 3: {
					System.out.print("ID da pessoa a ser alterada: ");
					int idAlterar = sc.nextInt();
					sc.nextLine();

					System.out.print("Novo nome: ");
					String nomeNovo = sc.nextLine();

					System.out.print("Novo email: ");
					String emailNovo = sc.nextLine();

					Pessoa pessoaAlterada = new Pessoa(nomeNovo, emailNovo);
					pDao.atualizar(idAlterar, pessoaAlterada);
					break;
				}

				// BUSCAR POR ID
				case 4: {
					System.out.print("ID a ser buscado: ");
					int idBuscar = sc.nextInt();
					sc.nextLine();

					Pessoa pessoaBuscada = pDao.buscarPorId(idBuscar);
					if (pessoaBuscada == null) {
						System.out.println("Não existe pessoa com ID " + idBuscar);
					} else {
						System.out.println(pessoaBuscada);
					}
					break;
				}

				// DELETAR
				case 5: {
					System.out.print("ID da pessoa a ser deletada: ");
					int idDeletar = sc.nextInt();
					sc.nextLine();
					pDao.deletar(idDeletar);
					break;
				}
				}
				break;
			}

			case 2: {
				System.out.println("------------------------------------------");
				System.out.println(
						"Escolha a operação a ser feita com funcionário:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por ID\n5 - Remover vínculo de funcionário");
				System.out.println("------------------------------------------");

				int respFunc = sc.nextInt();
				sc.nextLine(); // limpar o buffer

				while (respFunc < 1 || respFunc > 5) {
					System.out.println("Por favor escolha entre uma das opções");
					respFunc = sc.nextInt();
					sc.nextLine();
				}

				switch (respFunc) {
				// LISTAR
				case 1: {
					List<Funcionario> lista = fDao.listarTodos();
					System.out.println("Lista de funcionários: " + lista.size());
					for (Funcionario f : lista) {
						System.out.println(f);
					}
					break;
				}

				// INSERIR
				case 2: {
					System.out.print("ID da pessoa já cadastrada: ");
					int id = sc.nextInt();
					sc.nextLine();

					System.out.print("Matrícula (ex: F001): ");
					String matricula = sc.nextLine();

					System.out.print("Departamento: ");
					String departamento = sc.nextLine();

					// buscar pessoa para verificar existência
					Pessoa p = pDao.buscarPorId(id);

					if (p == null) {
						System.out.println("Erro: não existe pessoa com esse ID. Cadastre a pessoa antes.");
					} else {
						Funcionario f = new Funcionario(id, p.getNome(), p.getEmail(), matricula, departamento);
						fDao.inserir(f);
					}
					break;
				}

				// ATUALIZAR
				case 3: {
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

				// BUSCAR POR ID
				case 4: {
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

				// DELETAR (remover vínculo, manter como pessoa)
				case 5: {
					System.out.print("ID do funcionário a ser desvinculado: ");
					int id = sc.nextInt();
					sc.nextLine();

					fDao.deletar(id);
					break;
				}
				}

				break;
			}

			case 3: {
				System.out.println("------------------------------------------");
				System.out.println(
						"Escolha a operação a ser feita com projeto:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por id\n5 - Deletar");
				System.out.println("------------------------------------------");
				int respProj = sc.nextInt();
				sc.nextLine(); // Limpar quebra de linha

				while (respProj < 1 || respProj > 5) {
					System.out.println("Por favor escolha entre uma das opções");
					respProj = sc.nextInt();
					sc.nextLine();
				}

				switch (respProj) {

				// LISTAR
				case 1: {
					List<Projeto> lista = projetoDao.listarTodos();
					System.out.println("Projetos cadastrados: " + lista.size());
					for (Projeto p : lista) {
						System.out.println(p);
					}
					break;
				}

				// INSERIR
				case 2: {
					System.out.print("Nome do projeto: ");
					String nome = sc.nextLine();
					System.out.print("Descrição: ");
					String desc = sc.nextLine();
					System.out.print("ID do funcionário responsável: ");
					int idFunc = sc.nextInt();
					sc.nextLine(); // Limpar

					Projeto novo = new Projeto(nome, desc, idFunc);
					projetoDao.inserir(novo);
					break;
				}

				// ATUALIZAR
				case 3: {
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

				// BUSCAR POR ID
				case 4: {
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

				// DELETAR
				case 5: {
					System.out.print("ID do projeto a ser deletado: ");
					int idDel = sc.nextInt();
					sc.nextLine();
					projetoDao.deletar(idDel);
					break;
				}
				}
				break;
			}

			case 4: {
				continuar = false;
				System.out.println("Sistema encerrado.");
				break;
			}
			}
		}

		sc.close();
	}
}
