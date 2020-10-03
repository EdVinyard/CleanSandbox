package com.example.rating;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("com.example.rating.Dependencies")
public class Dependencies {

    @Bean
    public com.example.rating.api.RatingSystem ratingSystem() {
        return new InMemoryRatingSystem();
    }
}
