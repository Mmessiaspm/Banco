package br.edu.ifpi.Model;

public class ContaPoupanca extends Conta {

    private double rendimento;
    private static int totaldeContasPoupanca=0;

    public static int getTotaldeContasPoupanca() {
        return ContaPoupanca.totaldeContasPoupanca;
    }

    public double getRendimento() {
        return this.rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public ContaPoupanca() {
        setSaldo(100.0);
        ContaPoupanca.totaldeContasPoupanca=ContaPoupanca.totaldeContasPoupanca + 1;
    }
@Override
    public boolean sacar(double valor) {
        if (valor > getSaldo()) {
            return false;
        } else {
            setSaldo(getSaldo() - valor);
            return true;
        }
    }

 
}