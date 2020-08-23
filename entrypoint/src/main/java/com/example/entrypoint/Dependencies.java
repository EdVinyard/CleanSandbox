package com.example.entrypoint;

import com.example.domain.Conversation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Dependencies {

    @Bean
    public Conversation chat() {
        return new Conversation();
    }
}
