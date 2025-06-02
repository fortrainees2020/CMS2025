package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TablePerClassDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("inheritance-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        FullTimeEmployee fte = new FullTimeEmployee("Priya", 80000);
        PartTimeEmployee pte = new PartTimeEmployee("Ashish", 40);

        em.persist(fte);
        em.persist(pte);

        em.getTransaction().commit();

        System.out.println("Saved employees with Table Per Class inheritance.");

        em.close();
        emf.close();
    }
}
