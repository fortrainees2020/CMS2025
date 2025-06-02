package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeeEmbeddedDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("employee-embed-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Create address object which you want to embed
        Address address = new Address("200 Elm St", "Metropolis", "California", "10001");
        
        // create Employee object with address object
        Employee employee = new Employee("Ron ", address);

        em.persist(employee); // Save the object to table 
        em.getTransaction().commit();

        // Retrieve and print the employee
        Employee found = em.find(Employee.class, employee.getId());
        System.out.println("Employee Name: " + found.getName());
        System.out.println("Address: " 
            + found.getAddress().getStreet() + ", " 
            + found.getAddress().getCity() + ", " 
            + found.getAddress().getState() + " " 
            + found.getAddress().getZipcode());

        em.close();
        emf.close();
    }
}
