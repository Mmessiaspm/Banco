package br.edu.ifpi.Principal;

import br.edu.ifpi.DAO.ClienteDAO;
import br.edu.ifpi.Model.Cliente;

public class Main {

    
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setNome("luis Mario");
        cliente.setDataNasc("01/01/2010");
        cliente.setEndereco("Rua A, 123");
        cliente.setTelefone("123456789");
        cliente.setSenha("senha123");  
        
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.salvar(cliente);
    }
}