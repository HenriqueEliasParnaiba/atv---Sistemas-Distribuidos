package app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Main {

        public static void main(String[] args) throws Exception {
        String name = System.getenv().getOrDefault("APP_NAME", "serverapp");
        int port = Integer.parseInt(System.getenv().getOrDefault("APP_PORT", "8083"));

        System.out.println("[" + name + "] Servidor iniciando na porta " + port);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/event", new Handler(name));
        server.start();
        System.out.println("[" + name + "] Pronto em /event");
    }

    static class Handler implements HttpHandler {
        private final String name;

        public Handler(String name) {
            this.name = name;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }
            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("[" + name + "] DESTINO recebeu: " + body + " @ " + LocalDateTime.now());
            byte[] resp = "OK".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, resp.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(resp);
            }
        }
    }
}
