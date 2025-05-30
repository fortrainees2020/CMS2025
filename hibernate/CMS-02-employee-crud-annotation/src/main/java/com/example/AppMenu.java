package com.example;

import java.util.List;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Employee CRUD Menu ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee by ID");
            System.out.println("3. View All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    sc.nextLine();  // consume leftover newline
                    String name = sc.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = sc.nextDouble();
                    EmpCRUD.addEmployee(name, salary);
                    break;

                case 2:
                    System.out.print("Enter employee ID: ");
                    int id = sc.nextInt();
                    Emp emp = EmpCRUD.getEmployee(id);
                    if (emp != null) {
                        System.out.println("ID: " + emp.getId());
                        System.out.println("Name: " + emp.getName());
                        System.out.println("Salary: " + emp.getSalary());
                    }
                    break;

                case 3:
                    List<Emp> employees = EmpCRUD.getAllEmployees();
                    System.out.println("All Employees:");
                    for (Emp e : employees) {
                        System.out.println(e.getId() + " | " + e.getName() + " | " + e.getSalary());
                    }
                    break;

                case 4:
                    System.out.print("Enter employee ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();  // consume newline
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new salary: ");
                    double newSalary = sc.nextDouble();
                    EmpCRUD.updateEmployee(updateId, newName, newSalary);
                    break;

                case 5:
                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = sc.nextInt();
                    EmpCRUD.deleteEmployee(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    EmpCRUD.shutdown();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}
