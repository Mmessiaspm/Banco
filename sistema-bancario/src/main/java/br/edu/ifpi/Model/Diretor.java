package br.edu.ifpi.Model;

public class Diretor extends Funcionario implements Autenticavel {

    private String departamento;
    private String senha;

    public String getDepartamento() {
        return this.departamento;
    }

    public String getSenha() {
        return this.senha;
    }   

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
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


