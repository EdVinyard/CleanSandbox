package com.example.boundedcontext1.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class GreeterTest {
    private static final Locale EN_US = Locale.forLanguageTag("en-US");
    private static final Locale EN_AU = Locale.forLanguageTag("en-AU");
    private static final Locale EN_GB = Locale.forLanguageTag("en-GB");

    @Test
    public void constructorPreconditions() {
        assertThrows(IllegalArgumentException.class, () ->
                new Greeter(null));
    }

    @Test
    public void greetInWithExactMatch() {
        // Arrange
        var howdy = new Greeting(EN_US, "Howdy!");
        var gday  = new Greeting(EN_AU, "G'day mate!");

        var repository = mock(GreetingRepository.class);
        when(repository.getByBaseLanguage(EN_US))
                .thenReturn(new Greetings(EN_US, List.of(howdy, gday)));

        var greeter = new Greeter(repository);

        // Act
        var actual = greeter.greetIn(EN_US);

        // Assert
        assertEquals(
            new Greetings(EN_US, List.of(howdy)),
            actual);
    }

    @Test
    public void greetInLackingExactMatch() {
        // Arrange
        var howdy = new Greeting(EN_US, "Howdy!");
        var gday  = new Greeting(EN_AU, "G'day mate!");

        var repository = mock(GreetingRepository.class);
        when(repository.getByBaseLanguage(EN_GB))
                .thenReturn(new Greetings(EN_GB, List.of(howdy, gday)));

        var greeter = new Greeter(repository);

        // Act
        var actual = greeter.greetIn(EN_GB);

        // Assert
        assertEquals(
            new Greetings(EN_GB, List.of(howdy, gday)),
            actual);
    }    
}
