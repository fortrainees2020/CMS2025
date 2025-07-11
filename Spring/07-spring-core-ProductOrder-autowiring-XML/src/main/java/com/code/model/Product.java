package com.code.model;
public class Product {
	
	private String productName;
	private double price;  

public Product() {
	    this.productName = "Laptop";  
	    this.price = 1000.00;  
	}
	
	
	public String getProductName() {
	    return productName;
	}
	
	public void setProductName(String productName) {
	    this.productName = productName;
	}
	
	
	public double getPrice() {
	    return price;
	}
	
	public void setPrice(double price) {
	    this.price = price;
	}
	
	@Override
	public String toString() {
	    return "Product [productName=" + productName + ", price=" + price + "]";
	}
}