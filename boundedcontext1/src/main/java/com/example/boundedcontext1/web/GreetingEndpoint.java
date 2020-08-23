package com.example.boundedcontext1.web;

import com.example.boundedcontext1.domain.Greeter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.util.Locale;

public class GreetingEndpoint implements HttpHandler {
    private final Greeter greeter;

    public GreetingEndpoint(Greeter greeter) {
        this.greeter = greeter;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            final var language = Locale.forLanguageTag(exchange
                    .getRequestHeaders()
                    .get("Accept-Language")
                    .get(0)
                    .split(",")[0]
                    .split(";")[0]);
            final byte[] response = greeter
                    .greetIn(language)
                    .toString()
                    .getBytes("UTF-8");

            exchange.getResponseHeaders().add(
                    "Content-Type",
                    "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);

            final var out = exchange.getResponseBody();
            out.write(response);
            out.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
