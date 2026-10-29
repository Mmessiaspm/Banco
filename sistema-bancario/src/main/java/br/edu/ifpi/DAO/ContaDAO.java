package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Conta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ContaDAO {
    public static void salvar(Conta conta) {
         
        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(conta);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public Conta buscarPorId(Long id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.find(Conta.class, id);
        }
    }

    public Conta buscarPorNumero(int numero) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            // Cria a consulta JPQL para buscar por código
            String jpql = "SELECT c FROM Conta c WHERE c.numero = :numero";
            TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
            query.setParameter("numero", numero);

            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
