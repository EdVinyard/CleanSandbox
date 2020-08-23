package com.example.entrypoint;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /** application entry point */
    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext(
                com.example.entrypoint.Dependencies.class)) {
            SpringApplication.run(Main.class, args);

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        }
    }
}
