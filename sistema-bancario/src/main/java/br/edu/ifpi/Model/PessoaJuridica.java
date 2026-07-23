package br.edu.ifpi.Model;
public class PessoaJuridica extends Pessoa {   

    private String cnpj;
    
    //metodos getters
    public String getCnpj(){
        return this.cnpj;
    }

    
    //metodos setters
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

}
