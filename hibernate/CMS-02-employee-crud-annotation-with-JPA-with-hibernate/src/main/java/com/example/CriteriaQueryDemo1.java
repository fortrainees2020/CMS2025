package com.example;



import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Scanner;

public class CriteriaQueryDemo1{

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emp-unit");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            int choice;

            do {
                System.out.println("\n--- Criteria API Query Menu ---");
                System.out.println("1. Show all employees");
                System.out.println("2. Employees with salary > 5000");
                System.out.println("3. Show employee names only");
                System.out.println("4. Employees ordered by salary DESC");
                System.out.println("5. Count total employees");
                System.out.println("6. Average salary");
                System.out.println("7. Employees with names starting with 'J'");
                System.out.println("8. Employees with salary between 3000 and 7000");
                System.out.println("9. Employees with salary above average");
                System.out.println("10. Employees with IDs IN (1, 3, 5)");
                System.out.println("11. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root);
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("All employees:");
                        emps.forEach(e -> System.out.println(e.getId() + ": " + e.getName() + " - " + e.getSalary()));
                        break;
                    }
                    case 2 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root).where(cb.greaterThan(root.get("salary"), 5000));
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary > 5000:");
                        emps.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 3 : {
                        CriteriaQuery<String> cq = cb.createQuery(String.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root.get("name"));
                        List<String> names = em.createQuery(cq).getResultList();
                        System.out.println("Employee names:");
                        names.forEach(System.out::println);
                        break;
                    }
                    case 4 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root).orderBy(cb.desc(root.get("salary")));
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("Employees ordered by salary DESC:");
                        emps.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 5 :{
                        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(cb.count(root));
                        Long count = em.createQuery(cq).getSingleResult();
                        System.out.println("Total employees: " + count);
                        break;
                    }
                    case 6 :{
                        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(cb.avg(root.get("salary")));
                        Double avg = em.createQuery(cq).getSingleResult();
                        System.out.println("Average salary: " + avg);
                        break;
                    }
                    case 7 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root).where(cb.like(root.get("name"), "J%"));
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("Employees with names starting with 'J':");
                        emps.forEach(e -> System.out.println(e.getName()));
                        break;
                    }
                    
                    case 8 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root).where(cb.between(root.get("salary"), 3000.0, 7000.0));
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary between 3000 and 7000:");
                        emps.forEach(e ->System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 9 : {
                        // First calculate average salary
                        CriteriaQuery<Double> cqAvg = cb.createQuery(Double.class);
                        Root<Emp> rootAvg = cqAvg.from(Emp.class);
                        cqAvg.select(cb.avg(rootAvg.get("salary")));
                        Double avgSalary = em.createQuery(cqAvg).getSingleResult();

                        // Then get employees with salary > avg
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root).where(cb.greaterThan(root.get("salary"), avgSalary));
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary above average (" + avgSalary + "):");
                        emps.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 10 :{
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(root).where(root.get("id").in(1, 3, 5));
                        List<Emp> emps = em.createQuery(cq).getResultList();
                        System.out.println("Employees with IDs 1, 3, 5:");
                        emps.forEach(e -> System.out.println(e.getId() + ": " + e.getName()));
                        break;
                    }
                    case 11 : System.out.println("Exiting...");break;
                    default : System.out.println("Invalid choice. Try again.");break;
                }

            } while (choice != 11);

        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}
