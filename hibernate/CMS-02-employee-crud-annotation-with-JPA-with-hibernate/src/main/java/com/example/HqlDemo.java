package com.example;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class HqlDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emp-unit");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {
            int choice;

            do {
                System.out.println("\n--- HQL Query Menu ---");
                System.out.println("1. Show all employees");
                System.out.println("2. Employees with salary > 5000");
                System.out.println("3. Show employee names only");
                System.out.println("4. Employees ordered by salary DESC");
                System.out.println("5. Count total employees");
                System.out.println("6. Average salary");
                System.out.println("7. Update salary of employee by ID");
                System.out.println("8. Delete employee by ID");
                System.out.println("9. Employees with name starting with 'A'");
                System.out.println("10. Employees with salary between 3000 and 7000");
                System.out.println("11. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                EntityTransaction tx;
                switch (choice) {
                    case 1:
                        List<Emp> allEmps = em.createQuery("FROM Emp", Emp.class).getResultList();
                        System.out.println("All Employees:");
                        allEmps.forEach(e -> System.out.println(e.getId() + ": " + e.getName() + " - " + e.getSalary()));
                        break;

                    case 2:
                        List<Emp> richEmps = em.createQuery("FROM Emp e WHERE e.salary > 5000", Emp.class).getResultList();
                        System.out.println("Employees with salary > 5000:");
                        richEmps.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;

                    case 3:
                        List<String> names = em.createQuery("SELECT e.name FROM Emp e", String.class).getResultList();
                        System.out.println("Employee Names:");
                        names.forEach(System.out::println);
                        break;

                    case 4:
                        List<Emp> sortedBySalary = em.createQuery("FROM Emp e ORDER BY e.salary DESC", Emp.class).getResultList();
                        System.out.println("Employees sorted by salary DESC:");
                        sortedBySalary.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;

                    case 5:
                        Long count = em.createQuery("SELECT COUNT(e) FROM Emp e", Long.class).getSingleResult();
                        System.out.println("Total number of employees: " + count);
                        break;

                    case 6:
                        Double avgSalary = em.createQuery("SELECT AVG(e.salary) FROM Emp e", Double.class).getSingleResult();
                        System.out.println("Average salary: " + avgSalary);
                        break;

                    case 7:
                        tx = em.getTransaction();
                        tx.begin();
                        System.out.print("Enter employee ID to update salary: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();
                        int updatedCount = em.createQuery("UPDATE Emp e SET e.salary = :salary WHERE e.id = :id")
                                .setParameter("salary", newSalary)
                                .setParameter("id", updateId)
                                .executeUpdate();
                        tx.commit();
                        System.out.println("Number of employees updated: " + updatedCount);
                        break;

                    case 8:
                        tx = em.getTransaction();
                        tx.begin();
                        System.out.print("Enter employee ID to delete: ");
                        int deleteId = sc.nextInt();
                        int deletedCount = em.createQuery("DELETE FROM Emp e WHERE e.id = :id")
                                .setParameter("id", deleteId)
                                .executeUpdate();
                        tx.commit();
                        System.out.println("Number of employees deleted: " + deletedCount);
                        break;

                    case 9:
                        List<Emp> jEmps = em.createQuery("FROM Emp e WHERE e.name LIKE 'A%'", Emp.class).getResultList();
                        System.out.println("Employees with names starting with 'A':");
                        jEmps.forEach(e -> System.out.println(e.getName()));
                        break;

                    case 10:
                        List<Emp> salaryRangeEmps = em.createQuery("FROM Emp e WHERE e.salary BETWEEN 3000 AND 7000", Emp.class).getResultList();
                        System.out.println("Employees with salary between 3000 and 7000:");
                        salaryRangeEmps.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;

                    case 11:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }

            } while (choice != 11);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}
