package com.cg;

import com.cg.entity.Role;
import com.cg.entity.User;
import com.cg.dao.UserRoleCRUD;
import java.util.List;
import java.util.Scanner;

public class UserMain {

    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	UserRoleCRUD crud = new UserRoleCRUD();

    	while (true) {
            System.out.println("\n===== USER & ROLE MENU =====");
            System.out.println("1. Create Role");
            System.out.println("2. Create User");
            System.out.println("3. Show Users by Role ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Role role = new Role();
                    System.out.print("Enter Role ID: ");
                    role.setRoleId(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Enter Role Name: ");
                    role.setRoleName(scanner.nextLine());
                    crud.createRole(role);
                    break;

                case "2":
                    User user = new User();

                    System.out.print("Enter User Name: ");
                    user.setUserName(scanner.nextLine());

                    System.out.print("Enter Password: ");
                    user.setUserPWD(scanner.nextLine());

                    System.out.print("Enter Role ID to assign: ");
                    int rid = Integer.parseInt(scanner.nextLine());

                    Role tempRole = crud.findRoleById(rid);
                    if (tempRole == null) {
                        System.out.println("Role ID " + rid + " not found. Please create the role first.");
                        break;
                    }

                    user.setRole(tempRole);
                    crud.createUser(user);
                    break;

                case "3":
                    System.out.print("Enter Role ID to search users: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    List<User> users = crud.getUsersByRoleId(searchId);
                    if (users != null && !users.isEmpty()) {
                        for (User u : users) {
                            System.out.println("User ID: " + u.getUserId() + ", Name: " + u.getUserName());
                        }
                    } else {
                        System.out.println("No users found.");
                    }
                    break;

                case "4":
                    System.out.println("Exiting.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }}
