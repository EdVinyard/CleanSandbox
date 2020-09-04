package com.example.boundedcontext1.web;

import com.example.boundedcontext1.domain.Greeter;
import com.example.boundedcontext1.web.dto.GreetingsDto;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.nio.charset.StandardCharsets;

public class GreetingEndpoint implements HttpHandler {
    private final Greeter greeter;
    private final JsonSerializer jsonSerializer;

    public GreetingEndpoint(
            final Greeter greeter, 
            final JsonSerializer jsonSerializer) {
        this.greeter = greeter;
        this.jsonSerializer = jsonSerializer;
    }

    @Override
    public void handle(final HttpExchange exchange) {
        try {
            // input
            final var acceptLanguageHeader = new AcceptLanguageRequestHeader(
                    exchange.getRequestHeaders());

            // delegate
            final var greetings = greeter.greetIn(
                    acceptLanguageHeader.preferredLanguage());

            // output
            final var dto = new GreetingsDto(greetings);
            final String responseBodyJson = jsonSerializer.toJson(dto);
            final byte[] responseBodyUtf8 = responseBodyJson
                    .getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add(
                    "Content-Type",
                    "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(200, responseBodyUtf8.length);
            
            final var out = exchange.getResponseBody();
            out.write(responseBodyUtf8);
            out.close();
        } catch (Exception ex) {
            // TODO: move to EndpointErrorHandler
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
