package com.example.boundedcontext1.h2;

import static com.example.boundedcontext1.h2.H2GreetingRepository.Row;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static java.util.stream.Collectors.toList;

import com.example.boundedcontext1.Dependencies;
import com.example.boundedcontext1.domain.Greeting;
import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Dependencies.class)
public class H2GreetingRepositoryTest {
    private static final Locale EN_US = Locale.forLanguageTag("en-US");
    private static final Locale EN_GB = Locale.forLanguageTag("en-GB");
    private static final Locale ES_ES = Locale.forLanguageTag("es-ES");

    @Autowired
    private H2GreetingRepository repository;

    @Autowired
    private H2GreetingRepository.TableGateway table;

    @Test
    public void getByBaseLanguage() {
        // Arrange
        var us = new Row();
        us.language = EN_US.toLanguageTag();
        us.text = "Howdy!";
        table.save(us);

        var gb = new Row();
        gb.language = EN_GB.toLanguageTag();
        gb.text = "Hello!";
        table.save(gb);

        var es = new Row();
        es.language = ES_ES.toLanguageTag();
        es.text = "¡Hola!";
        table.save(es);

        // Act
        var actual = repository.getByBaseLanguage(EN_US);

        // Assert
        assertEquals(EN_US, actual.preferredLanguage());
        assertEquals(2, actual.size());

        var actualGreetingTexts = StreamSupport
                .stream(actual.spliterator(), false)
                .map(Greeting::text)
                .sorted()
                .collect(toList());
        assertIterableEquals(
                List.of("Hello!", "Howdy!"),
                actualGreetingTexts);
    }
}
