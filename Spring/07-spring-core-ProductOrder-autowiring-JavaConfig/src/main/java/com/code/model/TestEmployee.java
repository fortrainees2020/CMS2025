package com.code.model;

import javax.swing.plaf.synth.SynthStyleFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployee {
	public static void main(String[] args) {  
		 
		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	        Order order = context.getBean(Order.class);
	        order.showOrderDetails();
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