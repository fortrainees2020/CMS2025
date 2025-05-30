package com.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmpCRUD {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure() // hibernate.cfg.xml
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initialization failed: " + e);
        }
    }

    // CREATE
    public static void addEmployee(String name, double salary) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Emp emp = new Emp();
        emp.setName(name);
        emp.setSalary(salary);

        session.save(emp);
        tx.commit();
        session.close();

        System.out.println("Saved Employee: " + emp.getId() + " - " + emp.getName());
    }

    // READ (by ID)
    public static Emp getEmployee(int id) {
        Session session = sessionFactory.openSession();
        Emp emp = session.get(Emp.class, id);
        session.close();

        if (emp != null) {
            System.out.println("Found Employee: " + emp.getName());
        } else {
            System.out.println("Employee not found.");
        }
        return emp;
    }

    // READ ALL
    public static List<Emp> getAllEmployees() {
        Session session = sessionFactory.openSession();
        List<Emp> list = session.createQuery("from Emp", Emp.class).list();
        session.close();

        return list;
    }

    // UPDATE
    public static void updateEmployee(int id, String newName, double newSalary) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Emp emp = session.get(Emp.class, id);
        if (emp != null) {
            emp.setName(newName);
            emp.setSalary(newSalary);
            session.update(emp);
            tx.commit();
            System.out.println("Updated Employee ID: " + id);
        } else {
            System.out.println("Employee not found.");
            tx.rollback();
        }
        session.close();
    }

    // DELETE
    public static void deleteEmployee(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Emp emp = session.get(Emp.class, id);
        if (emp != null) {
            session.delete(emp);
            tx.commit();
            System.out.println("Deleted Employee ID: " + id);
        } else {
            System.out.println("Employee not found.");
            tx.rollback();
        }
        session.close();
    }

    // CLOSE factory when app ends
    public static void shutdown() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
