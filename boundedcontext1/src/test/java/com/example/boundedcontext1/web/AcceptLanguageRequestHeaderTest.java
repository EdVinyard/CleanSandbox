package com.example.boundedcontext1.web;

import com.sun.net.httpserver.Headers;
import java.util.Locale;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcceptLanguageRequestHeaderTest {
    @Test
	void preferredLanguage() {
        // Arrange
        final var headers = new Headers();
        headers.add("Accept-Language", "en-US,en;q=0.5");

        // Act
        final var actual = new AcceptLanguageRequestHeader(headers);

        // Assert
        assertEquals(Locale.forLanguageTag("en-US"), actual.preferredLanguage());
	}
}
