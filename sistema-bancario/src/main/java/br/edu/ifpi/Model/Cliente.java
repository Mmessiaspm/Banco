package br.edu.ifpi.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa implements Autenticavel {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     private String endereco;
     private String telefone;
     private String senha;

      public String getSenha() {
         return this.senha;
     }
     
      public void setSenha(String senha) {
         this.senha = senha;
     }

     public String getEndereco() {
         return this.endereco;
     }

     public String getTelefone() {
         return this.telefone;
     }

     public void setEndereco(String endereco) {
         this.endereco = endereco;
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

}
