package com.example.entrypoint;

import java.io.PrintStream;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /** application entry point */
    public static void main(String[] args) throws Exception {
        final var ctx = createApplicationContext();
        printBeanNames(System.out, ctx);
        Runtime.getRuntime().addShutdownHook(new OnShutdown(ctx));
        ctx.getBean(com.example.boundedcontext1.web.WebService.class)
                .start();
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

    private static class OnShutdown extends Thread {
        private final AnnotationConfigApplicationContext ctx;

        public OnShutdown(AnnotationConfigApplicationContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            System.out.println("Closing the ApplicationContext...");
            if (null != ctx) {
                ctx.close();
            }
        }
    }
}
