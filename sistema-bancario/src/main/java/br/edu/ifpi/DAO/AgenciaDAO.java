package br.edu.ifpi.DAO;

import br.edu.ifpi.Model.Agencia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AgenciaDAO {

    public void salvar(Agencia agencia) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(agencia);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public Agencia buscarPorId(Long id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.find(Agencia.class, id);
        }
    }

    public Agencia buscarPorNumero(int numero) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            // Cria a consulta JPQL para buscar por código
            String jpql = "SELECT a FROM Agencia a WHERE a.numero = :numero";
            TypedQuery<Agencia> query = em.createQuery(jpql, Agencia.class);
            query.setParameter("numero", numero);

            // Retorna o resultado da consulta, ou null se não for encontrado
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
