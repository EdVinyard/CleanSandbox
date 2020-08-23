package com.example.boundedcontext1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Locale;

public class GreetingsTest {
    private static final Locale EN_US = Locale.forLanguageTag("en-US");
    private static final Locale EN_AU = Locale.forLanguageTag("en-AU");

    @Test 
    public void constructorAndAccessorMethods() {
        // Arrange
        var hi = new Greeting(EN_US, "Hi!");
        var hello = new Greeting(EN_US, "Hello!");
        var gday = new Greeting(EN_AU, "G’day mate!");

        // Act
        var actual = new Greetings(EN_US, List.of(hi, hello, gday));

        // Assert
        assertEquals(EN_US, actual.preferredLanguage());
        
        var it = actual.iterator();
        assertEquals(hi, it.next());
        assertEquals(hello, it.next());
        assertEquals(gday, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void constructorPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> 
                new Greetings(null, List.of(new Greeting(EN_US, "Hi!"))));
        assertThrows(IllegalArgumentException.class, () -> 
                new Greetings(EN_US, null));
    }

    @Test
    public void toStringTest() {
        // Arrange
        var hi = new Greeting(EN_US, "Hi!");
        var hello = new Greeting(EN_US, "Hello!");

        // Act
        var actual = new Greetings(EN_US, List.of(hi, hello));
        
        // Assert
        assertEquals(
            "[prefer en_US; Hi! (en_US), Hello! (en_US)]",
            actual.toString());
    }

    @Test
    public void hashCodeTest() {
        // Arrange
        var hi = new Greeting(EN_US, "Hi!");
        var hello = new Greeting(EN_US, "Hello!");

        // Act
        var hiHelloUS = new Greetings(EN_US, List.of(hi, hello));
        var helloHiUS = new Greetings(EN_US, List.of(hello, hi));
        var helloHiAU = new Greetings(EN_AU, List.of(hello, hi));

        // Assert
        assertEquals(
            hiHelloUS.hashCode(), 
            hiHelloUS.hashCode()); // stable over time
        assertNotEquals(
            helloHiAU.hashCode(), 
            helloHiUS.hashCode()); // differ by preferred lang
        assertNotEquals(
            hiHelloUS.hashCode(), 
            helloHiUS.hashCode()); // differ by order
    }

    @Test
    public void equalsTest() {
        // Arrange
        var hi = new Greeting(EN_US, "Hi!");
        var hello = new Greeting(EN_US, "Hello!");

        // Act
        var hiUS = new Greetings(EN_US, List.of(hi));
        var hiHelloUS = new Greetings(EN_US, List.of(hi, hello));
        var helloHiUS = new Greetings(EN_US, List.of(hello, hi));
        var helloHiAU = new Greetings(EN_AU, List.of(hello, hi));

        // Assert
        assertNotEquals(
            hiUS.hashCode(), 
            hiHelloUS.hashCode()); // differ by length of list
        assertNotEquals(
            helloHiAU.hashCode(), 
            helloHiUS.hashCode()); // differ by preferred lang
        assertNotEquals(
            hiHelloUS.hashCode(), 
            helloHiUS.hashCode()); // differ by order
    }
}
