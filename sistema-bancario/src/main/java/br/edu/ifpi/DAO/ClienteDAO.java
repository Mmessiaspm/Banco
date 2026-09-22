package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                if (cliente.getEndereco() != null) {
                    em.persist(cliente.getEndereco());
                }
                em.persist(cliente);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public Cliente buscarPorId(Long id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.find(Cliente.class, id);
        }
    }

    public Cliente buscarPorCpf(String cpf) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            // Cria a consulta JPQL para buscar por CPF
            String jpql = "SELECT p FROM Cliente p WHERE p.cpf = :cpf";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("cpf", cpf);
            
            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    



}
