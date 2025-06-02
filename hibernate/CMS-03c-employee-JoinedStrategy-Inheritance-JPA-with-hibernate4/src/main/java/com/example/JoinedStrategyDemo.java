package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JoinedStrategyDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("inheritance-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        FullTimeEmployee fte = new FullTimeEmployee("Alice", 90000);
        PartTimeEmployee pte = new PartTimeEmployee("Bob", 45);

        em.persist(fte);
        em.persist(pte);

        em.getTransaction().commit();

        System.out.println("Entities persisted using JOINED inheritance strategy.");

        em.close();
        emf.close();
    }
}
