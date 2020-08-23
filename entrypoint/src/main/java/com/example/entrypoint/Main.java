package com.example.entrypoint;

import com.example.boundedcontext1.web.WebService;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /** application entry point */
    public static void main(String[] args) throws Exception {
        try (var ctx = new AnnotationConfigApplicationContext(
                com.example.boundedcontext1.Dependencies.class)) {
            SpringApplication.run(Main.class, args);

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            ctx.getBean(WebService.class).start();
        }
    }
}
