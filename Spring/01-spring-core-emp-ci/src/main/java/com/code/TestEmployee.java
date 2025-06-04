package com.code;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmployee {
	public static void main(String[] args) {  
      ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
      Employee employee= context.getBean("empbean",Employee.class);
    System.out.println("Id :"+ employee.getId());
    System.out.println("Name :"+ employee.getName());
      employee.show();
    } 
}
/*
1. Create simple maven project
2. Create EmployeeBean
3. Write the application.xml
4. Write main()
5. Run
*/