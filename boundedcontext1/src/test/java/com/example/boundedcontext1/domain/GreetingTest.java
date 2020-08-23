package com.example.boundedcontext1.domain;

import java.util.Locale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GreetingTest {
    private static final Locale EN_US = Locale.forLanguageTag("en-US");
    private static final Locale EN_GB = Locale.forLanguageTag("en-GB");

    @Test
    public void constructorAndPropertyGetterMethods() {
        // Arrange
        var expectedLanguage = EN_US;
        var expectedText = "Hello!";

        // Act
        var greeting = new Greeting(expectedLanguage, expectedText);

        // Assert
        assertEquals(expectedLanguage, greeting.language());
        assertEquals(expectedText, greeting.text());
    }

    @Test
    public void constructorPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> 
                new Greeting(null, "x"));
        assertThrows(IllegalArgumentException.class, () -> 
                new Greeting(EN_US, null));
        assertThrows(IllegalArgumentException.class, () ->
                new Greeting(EN_US, ""));
        assertThrows(IllegalArgumentException.class, () ->
                new Greeting(EN_US, " "));
        assertThrows(IllegalArgumentException.class, () ->
                new Greeting(EN_US, "\t"));
        assertThrows(IllegalArgumentException.class, () ->
                new Greeting(EN_US, "\n"));
    }

    @Test
    public void toStringTest() {
        // Arrange
        var greeting = new Greeting(EN_US, "Hello!");

        // Act and Assert
        assertEquals("Hello! (en_US)", greeting.toString()); // expected failure
    }

    @Test
    public void hashCodeTest() {
        // Arrange
        var hi = new Greeting(EN_US, "Hi!");
        var helloUS = new Greeting(EN_US, "Hello!");
        var helloGB = new Greeting(EN_GB, "Hello!");
        
        // Act and Assert

        // hash code should be stable over time
        assertEquals(hi.hashCode(), hi.hashCode());

        // hash code should vary by locale, text, or both
        assertNotEquals(helloUS.hashCode(), helloGB.hashCode());// locale differs
        assertNotEquals(helloUS.hashCode(), hi.hashCode());     // text differs
        assertNotEquals(helloGB.hashCode(), hi.hashCode());     // both differ
    }

    @Test
    public void equalsTest() {
        // Arrange
        var hi = new Greeting(EN_US, "Hi!");
        var helloUS = new Greeting(EN_US, "Hello!");
        var helloGB = new Greeting(EN_GB, "Hello!");
        
        // Act and Assert
        assertEquals(hi, hi);

        assertNotEquals(helloUS, helloGB);  // locale differs
        assertNotEquals(helloUS, hi);       // text differs
        assertNotEquals(helloGB, hi);       // both differ
    }
}
