package br.edu.ifpi.Model;
public class PessoaFisica extends Pessoa {

    private String cpf;
   
    //metodos getters
    public String getCpf(){
        return this.cpf;
    }

    
    //metodos setters
    public void setCpf(String cpf){
        this.cpf = cpf;
    }


}
