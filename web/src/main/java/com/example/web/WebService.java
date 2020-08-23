package com.example.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class WebService {
    static final int port = 8080;

    /** start the web server in a background thread */
    public void start() throws Exception {
        final var server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", this::hello);

        System.out.println("=================================================");
        System.out.println("Starting web service on port " + port + "...");
        System.out.println("Press Ctrl-C to stop web service and exit.");
        System.out.println("=================================================");
        server.start();
    }

    private void hello(HttpExchange exchange) {
        try {
            final byte[] response = "Hello, World!".getBytes("UTF-8");

            exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.length);

            final var out = exchange.getResponseBody();
            out.write(response);
            out.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
}
