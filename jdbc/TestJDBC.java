package jdbc;

import java.util.Scanner;

public class TestJDBC {

    public static void main(String[] args) {
        JavaUtil util = new JavaUtil();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Insert User");
            System.out.println("2. View All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 : {
                	//Accept the data from user 
                    System.out.print("Enter id: ");
                    long id = sc.nextLong();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter first name: ");
                    String first = sc.nextLine();
                    System.out.print("Enter last name: ");
                    String last = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    //Populate the user object from user input
                    User newUser = new User(id, first, last, email);
                    //Call to insertUser and passing the new user 
                    util.insertUser(newUser);
                  
                }
                break;
                case 2 : util.displayRecord();break;

                case 3 : {
                    System.out.print("Enter id of user to update: ");
                    long id = sc.nextLong();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter new first name: ");
                    String first = sc.nextLine();
                    System.out.print("Enter new last name: ");
                    String last = sc.nextLine();
                    System.out.print("Enter new email: ");
                    String email = sc.nextLine();

                    User updateUser = new User(id, first, last, email);
                    util.updateRecord(updateUser);
                }
                break;
                case 4 : {
                    System.out.print("Enter id of user to delete: ");
                    long id = sc.nextLong();
                    sc.nextLine(); // consume newline
                    util.deleteRecord(id);
                }
                break;
                case 5 : System.out.println("Exiting...");break;
                default : System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
