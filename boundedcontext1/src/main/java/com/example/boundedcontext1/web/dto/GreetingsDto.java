package com.example.boundedcontext1.web.dto;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

import java.util.List;

public class GreetingsDto {
    public String preferredLanguage;
    public List<GreetingDto> greetings;

    public GreetingsDto() {
    }

    public GreetingsDto(com.example.boundedcontext1.domain.Greetings greetings) {
        this.preferredLanguage = greetings.preferredLanguage().toLanguageTag();
        this.greetings = stream(greetings.spliterator(), false)
                .map(GreetingDto::new)
                .collect(toList());
    }
}
