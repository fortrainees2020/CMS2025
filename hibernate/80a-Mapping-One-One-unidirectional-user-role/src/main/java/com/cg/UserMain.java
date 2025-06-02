package com.cg;

import java.util.List;
import java.util.Scanner;

import com.cg.dao.UserRoleCRUD;
import com.cg.entity.Role;
import com.cg.entity.User;

public class UserMain {
	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    UserRoleCRUD crud = new UserRoleCRUD();

	    while (true) {
	        System.out.println("\n======= USER ROLE MANAGEMENT =======");
	        System.out.println("1. Create Role");
	        System.out.println("2. Create User");
	        System.out.println("3. Get User by ID");
	        System.out.println("4. Update User");
	        System.out.println("5. Delete User");
	        System.out.println("6. Get All Users");
	        System.out.println("7. Exit");
	        System.out.print("Choose an option: ");

	        int choice = Integer.parseInt(scanner.nextLine());

	        switch (choice) {
	            case 1: //Create Role
	                System.out.print("Enter role name: ");
	                String roleName = scanner.nextLine();
	                
	                Role role = new Role();
	                role.setRoleName(roleName);
	                
	                crud.createRole(role);
	                
	                System.out.println("Role created.");
	                break;

	            case 2: //Create User
	                System.out.print("Enter user name: ");
	                String userName = scanner.nextLine();
	                
	                System.out.print("Enter password: ");
	                String userPWD = scanner.nextLine();
	                
	                System.out.print("Enter role ID to assign: ");
	                int roleId = Integer.parseInt(scanner.nextLine());

	                Role userRole = new Role();
	                userRole.setRoleId(roleId);

	                User user = new User();
	                user.setUserName(userName);
	                user.setUserPWD(userPWD);
	                user.setRole(userRole);

	                crud.createUser(user);
	                System.out.println("User created.");
	                break;

	            case 3: //Get User by ID
	                System.out.print("Enter user ID: ");
	                int getId = Integer.parseInt(scanner.nextLine());
	                User fetchedUser = crud.getUserById(getId);
	                if (fetchedUser != null) {
	                    System.out.println("User: " + fetchedUser.getUserName() + 
	                                       ", Password: " + fetchedUser.getUserPWD() + 
	                                       ", Role: " + fetchedUser.getRole().getRoleName());
	                } else {
	                    System.out.println("User not found.");
	                }
	                break;

	            case 4: // Update User
	                System.out.print("Enter user ID to update: ");
	                int updateId = Integer.parseInt(scanner.nextLine());
	                
	                System.out.print("Enter new name: ");
	                String newName = scanner.nextLine();
	                
	                System.out.print("Enter new password: ");
	                String newPWD = scanner.nextLine();
	                
	                crud.updateUser(updateId, newName, newPWD);
	                
	                System.out.println("User updated.");
	                break;

	            case 5: //Delete User
	                System.out.print("Enter user ID to delete: ");
	                int deleteId = Integer.parseInt(scanner.nextLine());
	                
	                crud.deleteUser(deleteId);
	                
	                System.out.println("User deleted.");
	                break;

	            case 6: //Get All Users
	                List<User> users = crud.getAllUsers();
	                users.forEach(u -> System.out.println("User ID: " + u.getUserId() +
	                                                      ", Name: " + u.getUserName() +
	                                                      ", Role: " + u.getRole().getRoleName()));
	                break;

	            case 7:
	                System.out.println("Exiting...");
	                scanner.close();
	                System.exit(0);
	                break;

	            default:
	                System.out.println("Invalid option. Try again.");
	        }
	    }
	}

}
