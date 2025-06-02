package com.cg;

import com.cg.entity.Role;
import com.cg.entity.User;
import com.cg.dao.UserRoleCRUD;

import java.util.ArrayList;
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
            System.out.println("4. Show Roles by User ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Create Role
                    Role role = new Role();
                    System.out.print("Enter Role Name: ");
                    role.setRoleName(scanner.nextLine());
                    crud.createRole(role);
                    System.out.println("Role created with ID: " + role.getRoleId());
                    break;

                case "2":
                    // Create User with multiple Roles
                    User user = new User();
                    System.out.print("Enter User Name: ");
                    user.setUserName(scanner.nextLine());

                    System.out.print("Enter Password: ");
                    user.setUserPWD(scanner.nextLine());

                    List<Role> assignedRoles = new ArrayList<>();
                    while (true) {
                        System.out.print("Enter Role ID to assign (or 'done' to finish): ");
                        String input = scanner.nextLine();
                        if (input.equalsIgnoreCase("done")) {
                            break;
                        }
                        try {
                            int roleId = Integer.parseInt(input);
                            Role r = crud.findRoleById(roleId);
                            if (r == null) {
                                System.out.println("Role ID " + roleId + " not found.");
                            } else {
                                assignedRoles.add(r);
                                System.out.println("Added Role: " + r.getRoleName());
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid role ID or 'done'.");
                        }
                    }
                    user.setRoles(assignedRoles);
                    crud.createUser(user);
                    System.out.println("User created with ID: " + user.getUserId());
                    break;

                case "3":
                    // Show users by Role ID
                    System.out.print("Enter Role ID: ");
                    int roleIdToSearch = Integer.parseInt(scanner.nextLine());
                    List<User> usersByRole = crud.getUsersByRoleId(roleIdToSearch);
                    if (usersByRole != null && !usersByRole.isEmpty()) {
                        System.out.println("Users with Role ID " + roleIdToSearch + ":");
                        for (User u : usersByRole) {
                            System.out.println("User ID: " + u.getUserId() + ", Name: " + u.getUserName());
                        }
                    } else {
                        System.out.println("No users found for Role ID " + roleIdToSearch);
                    }
                    break;

                case "4":
                    // Show roles by User ID
                    System.out.print("Enter User ID: ");
                    int userIdToSearch = Integer.parseInt(scanner.nextLine());
                    List<Role> rolesByUser = crud.getRolesByUserId(userIdToSearch);
                    if (rolesByUser != null && !rolesByUser.isEmpty()) {
                        System.out.println("Roles assigned to User ID " + userIdToSearch + ":");
                        for (Role r : rolesByUser) {
                            System.out.println("Role ID: " + r.getRoleId() + ", Name: " + r.getRoleName());
                        }
                    } else {
                        System.out.println("No roles found for User ID " + userIdToSearch);
                    }
                    break;

                case "5":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
