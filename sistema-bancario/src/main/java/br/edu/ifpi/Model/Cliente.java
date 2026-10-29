package br.edu.ifpi.Model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa implements Autenticavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "senha", nullable = false)   
    private String senha;
    
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "cliente_conta", 
        joinColumns = @JoinColumn(name = "cliente_id"), 
        inverseJoinColumns = @JoinColumn(name = "conta_id")
    )
    private Set<Conta> contas = new HashSet<>();
    

      public String getSenha() {
         return this.senha;
     }
     
      public void setSenha(String senha) {
         this.senha = senha;
     }

     

     public Set<Conta> getContas() {
        return contas;
    }

      public void setContas(Set<Conta> contas) {
          this.contas = contas;
      }

     public String getTelefone() {
         return this.telefone;
     }

   

     public void setTelefone(String telefone) {
         this.telefone = telefone;
     }

        @Override
        public boolean autenticar(String senha) {
            if(this.senha.equals(senha)){
                return true;
            }
            return false;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

   

}
