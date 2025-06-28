package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.daos.FuncionarioDao;
import org.example.daos.PessoaDao;
import org.example.models.Pessoa;

public class Main {
    public static void main(String[] args) {

        // DAO
        PessoaDao pDao = new PessoaDao();
        FuncionarioDao fDao = new FuncionarioDao();

        // Scanner
        Scanner sc = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("--------------------------------------");
            System.out.println("Escolha a entidade a ser gerenciada:\n1 - Pessoa\n2 - Funcionário\n3 - Projeto\n4 - Sair");
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
                    System.out.println("Escolha a operação a ser feita com pessoa:\n1 - Listar\n2 - Inserir\n3 - Atualizar\n4 - Buscar por id\n5 - Deletar");
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
                    // A implementar: funcionalidades para Funcionário
                    break;
                }

                case 3: {
                    // A implementar: funcionalidades para Projeto
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
