package com.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HelloPrinter {

    @Autowired
    private HelloService helloService;

    @jakarta.annotation.PostConstruct
    public void print() {
        System.out.println(helloService.getMessage());
    }
}
