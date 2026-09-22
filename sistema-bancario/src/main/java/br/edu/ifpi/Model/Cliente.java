package br.edu.ifpi.Model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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


      public String getSenha() {
         return this.senha;
     }
     
      public void setSenha(String senha) {
         this.senha = senha;
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
