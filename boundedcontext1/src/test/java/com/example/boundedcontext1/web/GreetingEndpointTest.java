package com.example.boundedcontext1.web;

import com.example.boundedcontext1.domain.Greeter;
import com.example.boundedcontext1.inmemory.InMemoryGreetingRepository;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GreetingEndpointTest {
    @Test
	void test() throws Exception {
        // Arrange
        var requestHeaders = new Headers();
        requestHeaders.add("Accept-Language", "en-US,en;q=0.5");

        var responseHeaders = new Headers();

        var responseBody = new ByteArrayOutputStream();

        var exchange = mock(HttpExchange.class);
        when(exchange.getRequestHeaders()).thenReturn(requestHeaders);
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        when(exchange.getResponseBody()).thenReturn(responseBody);

        var endpoint = new GreetingEndpoint(
            new Greeter(new InMemoryGreetingRepository()),
            new GsonJsonSerializer());

        // Act
        endpoint.handle(exchange);

        // Assert
        var expectedResponseBodyJson = """
                {
                  "preferredLanguage": "en-US",
                  "greetings": [
                    {
                      "language": "en-US",
                      "text": "Howdy!"
                    }
                  ]
                }""";

        var expectedResponseBodyUtf8 = expectedResponseBodyJson.getBytes(
                StandardCharsets.UTF_8);
                
        verify(exchange).sendResponseHeaders(
                200,
                expectedResponseBodyUtf8.length);
        verify(exchange).getResponseBody();

        assertEquals(
                expectedResponseBodyJson,
                new String(responseBody.toByteArray(), StandardCharsets.UTF_8));
    }
}
