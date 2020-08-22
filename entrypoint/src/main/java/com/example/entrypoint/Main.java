package com.example.entrypoint;

import com.example.springbootweb.SpringbootwebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootwebApplication.class, args);
    }
}
