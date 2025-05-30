package com.example;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Scanner;

public class CriteriaQueryDemo2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emp-unit");
        EntityManager em = emf.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {
        	//Get the reference of criteriabuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();
            int choice;

            do {
                System.out.println("\n--- Criteria API Query Menu (Set 2) ---");
                System.out.println("1. Employees with salary < 4000");
                System.out.println("2. Employees whose name contains 'a'");
                System.out.println("3. Employees ordered by name ASC");
                System.out.println("4. Employees with salary != 5000");
                System.out.println("5. Maximum salary");
                System.out.println("6. Minimum salary");
                System.out.println("7. Employees with salary >= 3000 AND name starts with 'M'");
                System.out.println("8. Employees with salary < 4000 OR name ends with 'a'");
                System.out.println("9. Employee names and salaries");
                System.out.println("10. Count employees grouped by salary");
                System.out.println("11. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 : {
                    	//Get the reference of Criteria query based on Class where you want to fire a query
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        // select * from emp where salary<4000
                        cq.select(root).where(cb.lessThan(root.get("salary"), 4000));
                       // Collec the results in a collection - list
                        List<Emp> list = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary < 4000:");
                        
                        //Traverse the result from collection - list
                        list.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 2 :{
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        
                        cq.select(root).where(cb.like(root.get("name"), "%a%"));
                        
                        List<Emp> list = em.createQuery(cq).getResultList();
                        
                        System.out.println("Employees whose name contains 'an':");
                        list.forEach(e -> System.out.println(e.getName()));
                        break;
                    }
                    case 3 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        
                        cq.select(root).orderBy(cb.asc(root.get("name")));
                        
                        List<Emp> list = em.createQuery(cq).getResultList();
                        System.out.println("Employees ordered by name ASC:");
                        list.forEach(e -> System.out.println(e.getName()));
                        break;
                    }
                    case 4 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        
                        cq.select(root).where(cb.notEqual(root.get("salary"), 5000));
                        
                        List<Emp> list = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary != 5000:");
                        list.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 5 : {
                        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(cb.max(root.get("salary")));
                        Double maxSalary = em.createQuery(cq).getSingleResult();
                        System.out.println("Maximum salary: " + maxSalary);
                        break;
                    }
                    case 6 : {
                        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.select(cb.min(root.get("salary")));
                        Double minSalary = em.createQuery(cq).getSingleResult();
                        System.out.println("Minimum salary: " + minSalary);
                        break;
                    }
                    case 7 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        
                        Predicate salaryGE = cb.ge(root.get("salary"), 3000);
                        Predicate nameStartsM = cb.like(root.get("name"), "M%");
                        
                        cq.select(root).where(cb.and(salaryGE, nameStartsM));
                        List<Emp> list = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary >= 3000 AND name starts with 'M':");
                        list.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 8 : {
                        CriteriaQuery<Emp> cq = cb.createQuery(Emp.class);
                        Root<Emp> root = cq.from(Emp.class);
                        Predicate salaryLT = cb.lt(root.get("salary"), 4000);
                        Predicate nameEndsA = cb.like(root.get("name"), "%a");
                        cq.select(root).where(cb.or(salaryLT, nameEndsA));
                        List<Emp> list = em.createQuery(cq).getResultList();
                        System.out.println("Employees with salary < 4000 OR name ends with 'a':");
                        list.forEach(e -> System.out.println(e.getName() + ": " + e.getSalary()));
                        break;
                    }
                    case 9 : {
                        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
                        Root<Emp> root = cq.from(Emp.class);
                        // seelct name, salary from EMP
                        cq.multiselect(root.get("name"), root.get("salary"));
                        
                        List<Object[]> list = em.createQuery(cq).getResultList();
                        
                        System.out.println("Employee names and salaries:");
                        for (Object[] row : list) {
                            System.out.println("Name: " + row[0] + ", Salary: " + row[1]);
                        }
                        break;
                    }
                    case 10 : {
                        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
                        Root<Emp> root = cq.from(Emp.class);
                        cq.multiselect(root.get("salary"), cb.count(root));
                        cq.groupBy(root.get("salary"));
                        List<Object[]> list = em.createQuery(cq).getResultList();
                        System.out.println("Count of employees per salary:");
                        for (Object[] row : list) {
                            System.out.println("Salary: " + row[0] + ", Count: " + row[1]);
                        }
                        break;
                    }
                    case 11 : System.out.println("Exiting...");break;
                    default : System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 11);

        } finally {
            em.close();
            emf.close();
            sc.close();
        }
    }
}
