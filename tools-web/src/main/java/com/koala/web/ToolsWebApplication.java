package com.koala.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.koala"})
public class ToolsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolsWebApplication.class, args);
    }

}
