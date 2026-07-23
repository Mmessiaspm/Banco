package br.edu.ifpi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @Column(name = "nome", nullable = false)
    private String nome; 

    @Column
    private String dataNasc;
    

    //metodos getters
    public String getNome(){
        return this.nome;
    }

    public String getDataNasc(){
        return this.dataNasc;
    }

    
    //metodos setters
    public void setNome(String nome){
        this.nome = nome;
    }

   public void setDataNasc(String dataNasc){
        this.dataNasc = dataNasc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
