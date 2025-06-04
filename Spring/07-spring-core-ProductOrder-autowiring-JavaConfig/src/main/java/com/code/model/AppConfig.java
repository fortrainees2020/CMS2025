package com.code.model;


import com.code.model.Order;
import com.code.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Product product() {
        Product product = new Product();
        product.setProductName("Smartphone");
        product.setPrice(800.00);
        return product;
    }

   
    @Bean
    public Order order() {
        Order order = new Order();
        order.setQuantity(3);  
        return order;
    }
}
