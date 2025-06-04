package com.code;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProduct {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); 
        ProductDAO dao = (ProductDAO) context.getBean("productDao");  
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Product Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Products");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Price: ");
                    int price = sc.nextInt();
                    Product newProduct = new Product();
                    newProduct.setId(id);
                    newProduct.setPname(name);
                    newProduct.setPrice(price);
                    int insertStatus = dao.saveProduct(newProduct);
                    System.out.println("Insert status: " + insertStatus);
                    break;

                case 2:
                    System.out.print("Enter ID of product to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new Price: ");
                    int newPrice = sc.nextInt();
                    Product updateProduct = new Product();
                    updateProduct.setId(uid);
                    updateProduct.setPname(newName);
                    updateProduct.setPrice(newPrice);
                    int updateStatus = dao.updateProduct(updateProduct);
                    System.out.println("Update status: " + updateStatus);
                    break;

                case 3:
                    System.out.print("Enter ID of product to delete: ");
                    int did = sc.nextInt();
                    Product deleteProduct = new Product();
                    deleteProduct.setId(did);
                    int deleteStatus = dao.deleteEmployee(deleteProduct);
                    System.out.println("Delete status: " + deleteStatus);
                    break;

                case 4:
                    List<Product> products = dao.getAllEmployees(); // better renamed to getAllProducts()
                    System.out.println("\n--- All Products ---");
                    for (Product p : products) {
                        System.out.println("ID: " + p.getId() + ", Name: " + p.getPname() + ", Price: " + p.getPrice());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}