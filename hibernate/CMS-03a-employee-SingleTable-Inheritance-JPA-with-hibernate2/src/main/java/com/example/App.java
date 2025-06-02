package com.example;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("inheritance-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        FullTimeEmployee ftEmp = new FullTimeEmployee("Shashidhar", 70000);
        PartTimeEmployee ptEmp = new PartTimeEmployee("Ankit", 25);

        em.persist(ftEmp);
        em.persist(ptEmp);

        em.getTransaction().commit();

        System.out.println("---- All Employees ----");
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();

        for (Employee e : employees) {
            System.out.print(e.getName() + " (" + e.getClass().getSimpleName() + ")");
            if (e instanceof FullTimeEmployee) {
                System.out.println(" - Salary: " + ((FullTimeEmployee) e).getSalary());
            } else if (e instanceof PartTimeEmployee) {
                System.out.println(" - Hourly Wage: " + ((PartTimeEmployee) e).getHourlyWage());
            } else {
                System.out.println();
            }
        }

        em.close();
        emf.close();
    }
}