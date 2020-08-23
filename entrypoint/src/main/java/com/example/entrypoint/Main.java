package com.example.entrypoint;

import java.io.PrintStream;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /** application entry point */
    public static void main(String[] args) throws Exception {
        try (var ctx = createApplicationContext()) {
            printBeanNames(System.out, ctx);
            ctx.getBean(com.example.boundedcontext1.web.WebService.class).start();
        }
    }

    static AnnotationConfigApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(
            com.example.boundedcontext1.Dependencies.class);
    }

    static void printBeanNames(
            final PrintStream out,
            final ListableBeanFactory beanFactory) {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        java.util.Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            out.println(beanName);
        }
    }
}
