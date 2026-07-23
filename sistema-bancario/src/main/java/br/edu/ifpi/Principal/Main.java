package br.edu.ifpi.Principal;

import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.Model.Cliente;

public class Main {

    
    public static void main(String[] args) {
        /*Cliente cliente = new Cliente();
        cliente.setNome("luis Mario");
        cliente.setCpf("12345678900");
        cliente.setDataNasc("01/01/2010");
        cliente.setEndereco("Rua A, 123");
        cliente.setTelefone("123456789");
        cliente.setSenha("senha123");  
        */
        ClienteDAO clienteDAO = new ClienteDAO();
        //clienteDAO.salvar(cliente);

        Cliente clienteBuscado = clienteDAO.buscarPorCpf(   "12345678911");
        if (clienteBuscado == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("Cliente encontrado: " + clienteBuscado.getNome());
        }
   

    }
}