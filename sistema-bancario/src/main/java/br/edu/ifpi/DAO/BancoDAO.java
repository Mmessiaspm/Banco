package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Banco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class BancoDAO {
 
    public void salvar(Banco banco) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(banco);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public Banco buscarPorId(Long id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.find(Banco.class, id);
        }
    }

    public Banco buscarPorNumero(int numero) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            // Cria a consulta JPQL para buscar por número
            String jpql = "SELECT b FROM Banco b WHERE b.numero = :numero";
            TypedQuery<Banco> query = em.createQuery(jpql, Banco.class);
            query.setParameter("numero", numero);

            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
