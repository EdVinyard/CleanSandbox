package com.example.boundedcontext1.web;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class WebService {
    private static final int PORT = 8080;
    private static final int BACKLOG_SIZE = 1;

    private final GreetingEndpoint greetingEndpoint;

    public WebService(final GreetingEndpoint greetingEndpoint) {
        this.greetingEndpoint = greetingEndpoint;
    }

    /** start the web server in a background thread */
    public void start() throws Exception {
        System.out.println("=================================================");
        System.out.println("Starting web service on port " + PORT + "...");
        System.out.println("Press Ctrl-C to stop web service and exit.");
        System.out.println("=================================================");

        final var server = HttpServer.create(
                new InetSocketAddress(PORT),
                BACKLOG_SIZE);

        final var onShutdown = new OnShutdown(server);
        Runtime.getRuntime().addShutdownHook(onShutdown);

        server.createContext("/", greetingEndpoint);

        server.start();
        System.out.println("Web service started.");
        onShutdown.join();
        System.out.println("Leaving WebService.start().");
    }

    private static class OnShutdown extends Thread {
        private final HttpServer server;

        OnShutdown(final HttpServer server) {
            this.server = server;
        }

        @Override
        public void run() {
            System.out.println("Stopping the web service...");
            server.stop(0);
        }
    }
}
