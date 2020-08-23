package com.example.entrypoint;

import com.example.domain.Greeter;
import com.example.web.GreetingEndpoint;
import com.example.web.WebService;
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
