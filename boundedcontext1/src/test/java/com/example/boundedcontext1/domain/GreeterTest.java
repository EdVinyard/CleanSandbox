package com.example.boundedcontext1.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreeterTest {
    @Test
    public void get() {
        // Arrange
        var greeter = new Greeter();

        // Act
        var actual = greeter.get();

        // Assert
        assertTrue(actual.length() > 0);
    }
}
