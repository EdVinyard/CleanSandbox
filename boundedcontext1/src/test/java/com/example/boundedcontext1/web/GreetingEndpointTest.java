package com.example.boundedcontext1.web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingEndpointTests {
    @Test
	void acceptHeaderParsing() {
        // en-US - should return one
        // en-GB - ditto
        // en-AU - ditto
        // en-ZA - should return three
        // en    - should return three
        // es-ES - should return zero
        fail("Write this test.");
	}
}
