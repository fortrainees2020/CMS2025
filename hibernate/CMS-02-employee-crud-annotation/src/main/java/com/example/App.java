package com.example;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

        // Create Session
        Session session = factory.openSession();

        try {
            // Start transaction
            session.beginTransaction();

            // Create Emp object
            Emp emp = new Emp();
            emp.setName("Johny");
            emp.setSalary(60000);

            // Save the object
            session.save(emp);

            // Commit transaction
            session.getTransaction().commit();

            System.out.println("Employee saved: " + emp.getName());
        } finally {
            session.close();
            factory.close();
        }
    }
}
