package com.code;

import javax.swing.plaf.synth.SynthStyleFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployee {
	public static void main(String[] args) {  
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
      Employee employee= (Employee) context.getBean("empbean",Employee.class);
      
      //Address address = (Address) context.getBean("add",Address.class);
      System.out.println(employee);
      
      Employee employee1= (Employee) context.getBean("empbean1",Employee.class);
  
    } 
}
/*
1. Create simple maven project
2. Create EmployeeBean
3. Write the application.xml
4. Write main()
5. Run
*/