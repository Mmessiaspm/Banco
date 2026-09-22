package br.edu.ifpi.Principal;

import java.util.Scanner;

import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.DAO.JPAUtil;
import br.edu.ifpi.Model.Cliente;
import br.edu.ifpi.Model.Endereco;

public class Main {

    //Menu principal do sistema bancário
   
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Fecha o EntityManagerFactory ao terminar a JVM
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            JPAUtil.close();
        }));

        ClienteDAO clienteDAO = new ClienteDAO();
        while (true) {
            System.out.println("Bem-vindo ao Sistema Bancário");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Cliente por CPF");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            String opcaoTexto = entrada.nextLine().trim();
            int opcao;

            try {
                opcao = Integer.parseInt(opcaoTexto);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            switch (opcao) {
                case 1:
                    Cliente cliente = new Cliente();
                    Endereco endereco = new Endereco();
                    try {
                        System.out.print("Digite o nome do cliente: ");
                        cliente.setNome(entrada.nextLine());
                        System.out.print("Digite o CPF do cliente: ");
                        cliente.setCpf(entrada.nextLine());
                        System.out.print("Digite a data de nascimento do cliente (dd/mm/aaaa): ");
                        cliente.setDataNasc(entrada.nextLine());
                        System.out.print("Digite o logradouro do cliente: ");
                        endereco.setLogradouro(entrada.nextLine());    
                        System.out.print("Digite número: ");
                        endereco.setNumero(entrada.nextLine());
                        System.out.print("Digite o complemento: ");
                        endereco.setComplemento(entrada.nextLine());
                        System.out.print("Digite o bairro: ");
                        endereco.setBairro(entrada.nextLine());
                        System.out.print("Digite a cidade: ");
                        endereco.setCidade(entrada.nextLine());
                        System.out.print("Digite o estado: ");
                        endereco.setEstado(entrada.nextLine());
                        System.out.print("Digite o CEP: ");
                        endereco.setCep(entrada.nextLine());
                        cliente.setEndereco(endereco);
                        System.out.print("Digite o telefone do cliente: ");
                        cliente.setTelefone(entrada.nextLine());
                        System.out.print("Digite a senha do cliente: ");
                        cliente.setSenha(entrada.nextLine());

                        clienteDAO.salvar(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Digite o CPF do cliente que deseja buscar: ");
                    String cpf = entrada.nextLine();
                    try {
                        Cliente clienteEncontrado = clienteDAO.buscarPorCpf(cpf);
                        if (clienteEncontrado != null) {
                            System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao ler o CPF: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Saindo do sistema...");
                    entrada.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}