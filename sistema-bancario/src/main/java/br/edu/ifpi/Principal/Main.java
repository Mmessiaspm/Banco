package br.edu.ifpi.Principal;

import java.util.Scanner;

import br.edu.ifpi.DAO.AgenciaDAO;
import br.edu.ifpi.DAO.BancoDAO;
import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.DAO.JPAUtil;
import br.edu.ifpi.Model.Agencia;
import br.edu.ifpi.Model.Banco;
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
        BancoDAO bancoDAO = new BancoDAO();
        AgenciaDAO agenciaDAO = new AgenciaDAO();
        while (true) {
            System.out.println("Bem-vindo ao Sistema Bancário");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Cliente por CPF");
            System.out.println("3. Cadastrar Banco");
            System.out.println("4. Buscar Banco por Código");
            System.out.println("5. Cadastrar Agência");
            System.out.println("6. Buscar Agência por Código");
            System.out.println("7. Sair");
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
                    // Implementar cadastro de banco
                    Banco banco = new Banco();
                    System.out.println("Cadastro de banco.");
                    System.out.print("Digite o nome do banco: ");   
                    String nomeBanco = entrada.nextLine();
                    banco.setNome(nomeBanco);
                    System.out.print("Digite o número do banco: ");
                    int numeroBanco = entrada.nextInt();
                    banco.setNumero(numeroBanco);
                    System.out.println(banco.getNumero());
                    System.out.println(numeroBanco);
                    bancoDAO.salvar(banco);
                    System.out.println("Banco cadastrado com sucesso!");
                    break;
                case 4:
                    // Implementar busca de banco por número
                    System.out.print("Digite o numero do banco que deseja buscar: ");
                    String numero = entrada.nextLine();
                    try {
                        Banco bancoEncontrado = bancoDAO.buscarPorNumero(Integer.parseInt(numero));
                        if (bancoEncontrado != null) {
                            System.out.println("Banco encontrado: " + bancoEncontrado.getNome());
                        } else {
                            System.out.println("Banco não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao ler o número do banco: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Cadastro de agencia.");
                    System.out.print("Digite o nome da agencia: ");   
                    String nomeAgencia = entrada.nextLine();
                    Agencia agencia = new Agencia();
                    agencia.setNome(nomeAgencia);
                    System.out.print("Digite o número da agencia: ");
                    int numeroAgencia = entrada.nextInt();
                    agencia.setNumero(numeroAgencia);
                    System.out.print("Digite o número do banco ao qual a agencia pertence: ");
                    int numeroBancoAgencia = entrada.nextInt();
                    Banco bancoAgencia = bancoDAO.buscarPorNumero(numeroBancoAgencia);
                    if (bancoAgencia == null) {
                        System.out.println("Banco não encontrado. Por favor, cadastre o banco primeiro.");
                        break;
                    }
                    agencia.setBanco(bancoAgencia); 
                    agenciaDAO.salvar(agencia);
                    System.out.println("Agencia cadastrada com sucesso!");
                    
                    break;
                case 6:
                      System.out.print("Digite o numero do agencia que deseja buscar: ");
                    int numeroAgencia1 = entrada.nextInt();
                    try {
                        Agencia agenciaEncontrada = agenciaDAO.buscarPorNumero(numeroAgencia1);
                        if (agenciaEncontrada != null) {
                            System.out.println("Agencia encontrada: " + agenciaEncontrada.getNome());
                        } else {
                            System.out.println("Agencia não encontrada.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao ler o número da agencia: " + e.getMessage());
                    }
                    
                    break;

                case 7:
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