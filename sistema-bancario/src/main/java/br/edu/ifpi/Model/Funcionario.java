
package br.edu.ifpi.Model;

public class Funcionario extends PessoaFisica {

    private String cargo;
    private double salario;

    public String getCargo() {
        return this.cargo;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

   


}
