package com.example.boundedcontext1.web;

import com.example.boundedcontext1.domain.Greeter;
import com.example.boundedcontext1.domain.Greeting;
import com.example.boundedcontext1.web.dto.GreetingDto;
import com.example.boundedcontext1.web.dto.GreetingsDto;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class GreetingEndpoint implements HttpHandler {
    private static final int METHOD_NOT_ALLOWED = 405;

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
            switch (exchange.getRequestMethod()) {
                case "POST":
                    post(exchange);
                    break;
                case "GET":
                    get(exchange);
                    break;
                default:
                    exchange.getResponseHeaders().add(
                            "Content-Type",
                            "application/json; charset=UTF-8");
                    exchange.sendResponseHeaders(METHOD_NOT_ALLOWED, 0L);
            }
        } catch (Exception ex) {
            // TODO: move to EndpointErrorHandler
            System.out.println("=============================================");
            System.out.println("ERROR: " + ex.toString());
            ex.printStackTrace(System.out);
            System.out.println("=============================================");
        }
    }

    private void get(final HttpExchange exchange) throws IOException {
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
    }

    private void post(final HttpExchange exchange) throws IOException {
        // input
        final var requestDto = jsonSerializer.fromJson(
                exchange.getRequestBody());

        // delegate
        final var greeting = greeter.remember(toDomain(requestDto));

        // output
        final var responseDto = new GreetingDto(greeting);
        final String responseBodyJson = jsonSerializer.toJson(responseDto);
        final byte[] responseBodyUtf8 = responseBodyJson
                .getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add(
                "Content-Type",
                "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(200, responseBodyUtf8.length);

        final var out = exchange.getResponseBody();
        out.write(responseBodyUtf8);
        out.close();
    }

    private Greeting toDomain(final GreetingDto dto) {
        return new Greeting(Locale.forLanguageTag(dto.language), dto.text);
    }
}
