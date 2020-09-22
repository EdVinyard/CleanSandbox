package com.example.boundedcontext1.web.dto;

public class GreetingDto {
    public String language;
    public String text;

    public GreetingDto() {
    }

    public GreetingDto(com.example.boundedcontext1.domain.Greeting g) {
        language = g.language().toLanguageTag();
        text = g.text();
    }
}
