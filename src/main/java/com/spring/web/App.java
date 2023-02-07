package com.spring.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);

        app.setDefaultProperties(Collections.singletonMap("server.port", "8091"));
        app.run(args);
    }
}