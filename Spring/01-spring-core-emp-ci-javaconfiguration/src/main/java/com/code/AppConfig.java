package com.code;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Employee empbean() {
        return new Employee(10, "Alex"); 
    }
}

/*
 * Declares a Spring Bean:

The @Bean annotation is used to declare a method as producing a Spring bean.
When Spring sees a method annotated with @Bean, it will invoke that method and register 
the return value as a Spring-managed bean.*/
 