package br.edu.ifpi.Model;



public abstract class Conta {

    private int numero;
    private Pessoa titular;
    private double saldo;
    

    public int getNumero() {
        return this.numero;
    }

    public Pessoa getTitular() {
        return this.titular;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTitular(Pessoa titular) {
        this.titular = titular;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    

    public abstract boolean sacar(double valor); 

    public void depositar(double valor){
        this.saldo += valor;
    }
}
