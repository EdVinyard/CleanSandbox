package com.example.boundedcontext1;

import com.example.boundedcontext1.domain.Greeter;
import com.example.boundedcontext1.web.GreetingEndpoint;
import com.example.boundedcontext1.web.WebService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Dependencies {

    @Bean
    public Greeter greeter() {
        return new Greeter();
    }

    @Bean
    public GreetingEndpoint greetingEndpoint(
            final Greeter greeter) {
        return new GreetingEndpoint(greeter);
    }

    @Bean
    public WebService webService(
            final GreetingEndpoint greetingEndpoint) {
        return new WebService(
            greetingEndpoint);
    }
}
