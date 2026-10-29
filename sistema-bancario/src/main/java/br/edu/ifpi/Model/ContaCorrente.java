package br.edu.ifpi.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta {

private double limite;

public double getLimite(){
    return this.limite;
}

public void setLimite(double limite){
    this.limite = limite;
}


    public boolean sacar(double valor) {
        if (valor > getSaldo() + this.limite) {
            return false;
        } else {
            setSaldo(getSaldo() - valor);
            return true;
        }
    }

   

}
