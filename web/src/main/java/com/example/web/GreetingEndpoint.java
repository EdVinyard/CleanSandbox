package com.example.web;

import com.example.domain.Greeter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GreetingEndpoint implements HttpHandler {
    private final Greeter greeter;

    public GreetingEndpoint(Greeter greeter) {
        this.greeter = greeter;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            final byte[] response = greeter.get().getBytes("UTF-8");

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
