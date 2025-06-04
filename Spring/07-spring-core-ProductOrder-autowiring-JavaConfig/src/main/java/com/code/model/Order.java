package com.code.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Order {

    private Product product;  
    private int quantity;  

    
    public Order() {
    }

  
    /*public void setProduct(Product product) {
        this.product = product; */

    @Autowired
    public void setProduct(Product product) {
        this.product = product;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to display order details
    public void showOrderDetails() {
        System.out.println("Order Details: ");
        System.out.println("Product: " + product.getProductName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Quantity: " + quantity);
        System.out.println("Total: " + (product.getPrice() * quantity));
    }
}