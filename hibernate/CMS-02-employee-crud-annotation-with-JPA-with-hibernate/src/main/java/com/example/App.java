package com.example;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emp-unit");
        EntityManager em = emf.createEntityManager();
        EmpService service = new EmpService(em);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. View All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();
                    service.createEmp(name, salary);
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    Emp emp = service.getEmp(id);
                    if (emp != null)
                        System.out.println(emp.getId() + ": " + emp.getName() + " - " + emp.getSalary());
                    else
                        System.out.println("Not found.");
                    break;

                case 3:
                    List<Emp> emps = service.getAllEmps();
                    emps.forEach(e -> System.out.println(e.getId() + ": " + e.getName() + " - " + e.getSalary()));
                    break;

                case 4:
                    System.out.print("ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Salary: ");
                    double newSalary = sc.nextDouble();
                    service.updateEmp(uid, newName, newSalary);
                    break;

                case 5:
                    System.out.print("ID to delete: ");
                    int did = sc.nextInt();
                    service.deleteEmp(did);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice != 6);

        em.close();
        emf.close();
        sc.close();
    }
}
