package com.example.boundedcontext1.h2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.example.boundedcontext1.Dependencies;
import com.example.boundedcontext1.domain.Greeting;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
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
    private GreetingTable table;

    @Test
    public void getByBaseLanguage() {
        // Arrange
        var us = new GreetingRow();
        us.language = EN_US.toLanguageTag();
        us.text = "Howdy!";
        table.save(us);

        var gb = new GreetingRow();
        gb.language = EN_GB.toLanguageTag();
        gb.text = "Hello!";
        table.save(gb);

        var es = new GreetingRow();
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
                .map(g -> g.text())
                .sorted()
                .collect(Collectors.toList());
        assertIterableEquals(
                List.of("Hello!", "Howdy!"),
                actualGreetingTexts);
    }
}
