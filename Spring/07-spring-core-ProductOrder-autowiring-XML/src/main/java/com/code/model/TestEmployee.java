package com.code.model;

import javax.swing.plaf.synth.SynthStyleFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployee {
	public static void main(String[] args) {  
		 
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the Order bean from the Spring context
        Order order = context.getBean("order", Order.class);

        // Display order details
        order.showOrderDetails();

        // Close the Spring context
        context.close();
     
    } 
}
/*
1. Create simple maven project
2. Create EmployeeBean
3. Write the application.xml
4. Write main()
5. Run
*/