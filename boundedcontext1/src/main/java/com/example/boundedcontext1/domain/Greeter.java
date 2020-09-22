package com.example.boundedcontext1.domain;

import static java.util.stream.Collectors.toList;

import java.util.Locale;
import java.util.stream.StreamSupport;

public class Greeter { // a use-case
    private final GreetingRepository repository;

    /**
     * will greet users in their preferred language, drawing from the Greetings
     * stored in the supplied Repository
     */
    public Greeter(GreetingRepository repository) {
        if (null == repository) {
            throw new IllegalArgumentException("repository must not be null");
        }

        this.repository = repository;
    }

    /**
     * greets the client in the preferred language when greetings are known in
     * that language; otherwise, returns all greetings that match at least the
     * base language (e.g., English) regardless of script, region, dialict, etc.
     */
    public Greetings greetIn(final Locale preferredLanguage) {
        var greetings = repository.getByBaseLanguage(preferredLanguage);

        var exactMatches = StreamSupport
                .stream(greetings.spliterator(), false)
                .filter(g -> preferredLanguage.equals(g.language()))
                .collect(toList());

        return exactMatches.isEmpty()
                ? greetings
                : new Greetings(preferredLanguage, exactMatches);
    }

    // TODO: document and test this use-case
    public Greeting remember(final Greeting greeting) {
        repository.save(greeting);
        return greeting;
    }
}
