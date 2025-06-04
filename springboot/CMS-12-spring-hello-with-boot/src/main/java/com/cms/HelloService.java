package com.cms;

import org.springframework.stereotype.Component;


import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getMessage() {
        return "Hello from Spring Boot!";
    }
}
