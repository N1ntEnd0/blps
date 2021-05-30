package com.example.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StackOverflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApplication.class, args);
    }

}
//ssh -L 5500:pg:5432 s250651@helios.cs.ifmo.ru -p 2222