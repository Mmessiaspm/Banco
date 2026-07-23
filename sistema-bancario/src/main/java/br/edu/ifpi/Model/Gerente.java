package br.edu.ifpi.Model;



public class Gerente extends Funcionario implements Autenticavel {

    private String setor;
    private String senha;

     public String getSenha() {
        return this.senha;
    }

    public String getSetor() {
        return this.setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

      public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public boolean autenticar(String senha) {
        // Implementar lógica de autenticação
        if (this.senha.equals(senha)) {
            return true;
        }
        return false;
    }

  

}
