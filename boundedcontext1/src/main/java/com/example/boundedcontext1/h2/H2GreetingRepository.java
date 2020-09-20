package com.example.boundedcontext1.h2;

import static java.util.stream.Collectors.toList;

import com.example.boundedcontext1.domain.Greeting;
import com.example.boundedcontext1.domain.GreetingRepository;
import com.example.boundedcontext1.domain.Greetings;
import java.util.Locale;

public class H2GreetingRepository implements GreetingRepository {
    // private static final Locale EN_US = Locale.forLanguageTag("en-US");
    // private static final Locale EN_AU = Locale.forLanguageTag("en-AU");
    // private static final Locale EN_GB = Locale.forLanguageTag("en-GB");

    // private static final List<Greeting> ALL_GREETINGS = List.of(new Greeting(EN_US, "Howdy!"),
    //         new Greeting(EN_GB, "Hello!"), new Greeting(EN_AU, "G'day mate!"));

    private GreetingTable table;

    public H2GreetingRepository(GreetingTable tableGateway) {
        this.table = tableGateway;
    }

    @Override
    public Greetings getByBaseLanguage(Locale language) {
        // final var matches = ALL_GREETINGS.stream()
        //   .filter(g -> language.getLanguage().equals(g.language().getLanguage()))
        //   .collect(toList());

        // return new Greetings(language, matches);

        // If `language` is 'en-US', .getLanguage() returns 'en'.
        var baseLanguage = language.getLanguage();

        return new Greetings(
                language,
                table.findByLanguageStartsWith(baseLanguage)
                        .stream()
                        .map(H2GreetingRepository::toGreeting)
                        .collect(toList()));
    }

    private static Greeting toGreeting(GreetingRow r) {
        return new Greeting(Locale.forLanguageTag(r.language), r.text);
    }
}
