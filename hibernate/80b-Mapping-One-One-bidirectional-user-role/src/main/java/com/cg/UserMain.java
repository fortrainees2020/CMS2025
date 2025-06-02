package com.cg;

import java.util.Scanner;

import com.cg.dao.UserRoleCRUD;
import com.cg.entity.Role;
import com.cg.entity.User;


public class UserMain {

	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    UserRoleCRUD crud = new UserRoleCRUD();

	    while (true) {
	        System.out.println("\n======= USER ROLE MENU =======");
	        System.out.println("1. Create User with Role (Bidirectional)");
	        System.out.println("2. Get User by ID");
	        System.out.println("3. Get Role by ID");
	        System.out.println("4. Exit");
	        System.out.print("Enter your choice: ");
	        
	        String choice = scanner.nextLine();

	        switch (choice) {
	            case "1":
	                // Create Role
	                System.out.print("Enter Role ID: ");
	                int roleId = Integer.parseInt(scanner.nextLine());

	                System.out.print("Enter Role Name: ");
	                String roleName = scanner.nextLine();

	                Role role = new Role();
	                role.setRoleId(roleId);
	                role.setRoleName(roleName);

	                // Create User
	                System.out.print("Enter User ID: ");
	                int userId = Integer.parseInt(scanner.nextLine());

	                System.out.print("Enter User Name: ");
	                String userName = scanner.nextLine();

	                System.out.print("Enter Password: ");
	                String password = scanner.nextLine();

	                User user = new User();
	                user.setUserId(userId);
	                user.setUserName(userName);
	                user.setUserPWD(password);

	                // Set bidirectional association
	                user.setRole(role);
	                role.setUser(user);

	                // Persist User (and Role)
	                crud.createUser(user);

	                System.out.println("User and Role created successfully.");
	                break;

	            case "2":
	                System.out.print("Enter User ID to fetch: ");
	                int fetchUserId = Integer.parseInt(scanner.nextLine());
	                User fetchedUser = crud.getUserById(fetchUserId);
	                if (fetchedUser != null) {
	                    System.out.println("Fetched User: " + fetchedUser.getUserName());
	                    System.out.println("User's Role: " + (fetchedUser.getRole() != null 
	                        ? fetchedUser.getRole().getRoleName() 
	                        : "No role assigned"));
	                } else {
	                    System.out.println("User not found.");
	                }
	                break;

	            case "3":
	                System.out.print("Enter Role ID to fetch: ");
	                int fetchRoleId = Integer.parseInt(scanner.nextLine());
	                Role fetchedRole = crud.getRoleById(fetchRoleId);
	                if (fetchedRole != null) {
	                    System.out.println("Fetched Role: " + fetchedRole.getRoleName());
	                    System.out.println("Role's User: " + (fetchedRole.getUser() != null 
	                        ? fetchedRole.getUser().getUserName() 
	                        : "No user assigned"));
	                } else {
	                    System.out.println("Role not found.");
	                }
	                break;

	            case "4":
	                System.out.println("Exiting program.");
	                scanner.close();
	                System.exit(0);

	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    }
	}

}
