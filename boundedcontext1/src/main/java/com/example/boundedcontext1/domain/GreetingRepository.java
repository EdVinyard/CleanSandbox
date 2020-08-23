package com.example.boundedcontext1.domain;

import java.util.Locale;

public interface GreetingRepository { // a data-access interface
    public Greetings getByBaseLanguage(Locale language);
}
