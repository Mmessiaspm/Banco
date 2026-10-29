package br.edu.ifpi.Principal;

import java.util.Scanner;

import br.edu.ifpi.DAO.AgenciaDAO;
import br.edu.ifpi.DAO.BancoDAO;
import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.DAO.ContaDAO;
import br.edu.ifpi.DAO.JPAUtil;
import br.edu.ifpi.Model.Agencia;
import br.edu.ifpi.Model.Banco;
import br.edu.ifpi.Model.Cliente;
import br.edu.ifpi.Model.ContaCorrente;
import br.edu.ifpi.Model.ContaPoupanca;
import br.edu.ifpi.Model.Endereco;

public class Main {

    //Menu principal do sistema bancário
   
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JPAUtil.close()));

        ClienteDAO clienteDAO = new ClienteDAO();
        BancoDAO bancoDAO = new BancoDAO();
        AgenciaDAO agenciaDAO = new AgenciaDAO();

        while (true) {
            System.out.println("Bem-vindo ao Sistema Bancário");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Buscar Cliente por CPF");
            System.out.println("3. Cadastrar Conta");
            System.out.println("4. Cadastrar Banco");
            System.out.println("5. Buscar Banco por Código");
            System.out.println("6. Cadastrar Agência");
            System.out.println("7. Buscar Agência por Código");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = lerInteiro(entrada);
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
                    System.out.println("Cadastro de Conta.");
                    System.out.print("Digite o tipo da conta (1) - Corrente, (2) - Poupança: ");
                    int tipoConta = lerInteiro(entrada);
                    ContaDAO contaDAO = new ContaDAO();
                    if (tipoConta == 1) {
                        ContaCorrente contaCorrente = new ContaCorrente();
                        System.out.print("Digite o número da conta corrente: ");
                        contaCorrente.setNumero(lerInteiro(entrada));

                        System.out.print("Digite o número da agência: ");
                        int numeroAgencia = lerInteiro(entrada);
                        Agencia agencia = agenciaDAO.buscarPorNumero(numeroAgencia);
                        if (agencia == null) {
                            System.out.println("Agência não encontrada. Por favor, cadastre a agência primeiro.");
                            break;
                        }
                        contaCorrente.setAgencia(agencia);

                        System.out.print("Digite o saldo inicial da conta corrente: ");
                        contaCorrente.setSaldo(lerDouble(entrada));

                        while (true) {
                            System.out.print("Digite o CPF do cliente para associar à conta (ou 'sair' para finalizar): ");
                            String cpfCliente = entrada.nextLine().trim();
                            if (cpfCliente.equalsIgnoreCase("sair")) {
                                break;
                            }
                            if (cpfCliente.isEmpty()) {
                                System.out.println("CPF inválido. Digite um CPF ou 'sair'.");
                                continue;
                            }

                            Cliente clienteDaConta = clienteDAO.buscarPorCpf(cpfCliente);
                            if (clienteDaConta == null) {
                                System.out.println("Cliente não encontrado. Por favor, cadastre o cliente primeiro.");
                                continue;
                            }

                            clienteDAO.associarConta(cpfCliente, contaCorrente);
                            break;
                        }

                        System.out.println("Conta Corrente cadastrada com sucesso!");
                        break;
                    }

                    if (tipoConta == 2) {
                        ContaPoupanca contaPoupanca = new ContaPoupanca();
                        System.out.print("Digite o número da conta poupança: ");
                        contaPoupanca.setNumero(lerInteiro(entrada));

                        System.out.print("Digite o número da agência: ");
                        int numeroAgenciaPoupanca = lerInteiro(entrada);
                        Agencia agenciaPoupanca = agenciaDAO.buscarPorNumero(numeroAgenciaPoupanca);
                        if (agenciaPoupanca == null) {
                            System.out.println("Agência não encontrada. Por favor, cadastre a agência primeiro.");
                            break;
                        }
                        contaPoupanca.setAgencia(agenciaPoupanca);

                        System.out.print("Digite o saldo inicial da conta poupança: ");
                        contaPoupanca.setSaldo(lerDouble(entrada));

                        while (true) {
                            System.out.print("Digite o CPF do cliente para associar à conta (ou 'sair' para finalizar): ");
                            String cpfCliente = entrada.nextLine().trim();
                            if (cpfCliente.equalsIgnoreCase("sair")) {
                                break;
                            }
                            if (cpfCliente.isEmpty()) {
                                System.out.println("CPF inválido. Digite um CPF ou 'sair'.");
                                continue;
                            }

                            Cliente clienteDaConta = clienteDAO.buscarPorCpf(cpfCliente);
                            if (clienteDaConta == null) {
                                System.out.println("Cliente não encontrado. Por favor, cadastre o cliente primeiro.");
                                continue;
                            }

                            clienteDAO.associarConta(cpfCliente, contaPoupanca);
                            break;
                        }
                

                        System.out.println("Conta Poupança cadastrada com sucesso!");
                        break;
                    }

                    System.out.println("Tipo de conta inválido. Tente novamente.");
                    break;

                case 4:
                    Banco banco = new Banco();
                    System.out.println("Cadastro de banco.");
                    System.out.print("Digite o nome do banco: ");
                    String nomeBanco = entrada.nextLine();
                    banco.setNome(nomeBanco);
                    System.out.print("Digite o número do banco: ");
                    banco.setNumero(lerInteiro(entrada));
                    bancoDAO.salvar(banco);
                    System.out.println("Banco cadastrado com sucesso!");
                    break;

                case 5:
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

                case 6:
                    System.out.println("Cadastro de agencia.");
                    System.out.print("Digite o nome da agencia: ");
                    String nomeAgencia = entrada.nextLine();
                    Agencia agencia = new Agencia();
                    agencia.setNome(nomeAgencia);
                    System.out.print("Digite o número da agencia: ");
                    agencia.setNumero(lerInteiro(entrada));
                    System.out.print("Digite o número do banco ao qual a agencia pertence: ");
                    int numeroBancoAgencia = lerInteiro(entrada);
                    Banco bancoAgencia = bancoDAO.buscarPorNumero(numeroBancoAgencia);
                    if (bancoAgencia == null) {
                        System.out.println("Banco não encontrado. Por favor, cadastre o banco primeiro.");
                        break;
                    }
                    agencia.setBanco(bancoAgencia);
                    agenciaDAO.salvar(agencia);
                    System.out.println("Agencia cadastrada com sucesso!");
                    break;

                case 7:
                    System.out.print("Digite o numero do agencia que deseja buscar: ");
                    int numeroAgencia1 = lerInteiro(entrada);
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

                case 8:
                    System.out.println("Saindo do sistema...");
                    entrada.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static int lerInteiro(Scanner entrada) {
        return Integer.parseInt(entrada.nextLine().trim());
    }

    private static double lerDouble(Scanner entrada) {
        return Double.parseDouble(entrada.nextLine().trim().replace(',', '.'));
    }
}