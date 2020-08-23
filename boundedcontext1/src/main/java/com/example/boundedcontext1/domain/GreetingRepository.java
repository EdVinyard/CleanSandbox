package com.example.boundedcontext1.domain;

import java.util.Locale;

public interface GreetingRepository {
    public Greetings getByBaseLanguage(Locale language);
}
