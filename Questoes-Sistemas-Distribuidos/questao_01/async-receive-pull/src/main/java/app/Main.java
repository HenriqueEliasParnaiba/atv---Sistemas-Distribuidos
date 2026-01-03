package app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Main {

       public static void main(String[] args) throws Exception {
        String name = System.getenv().getOrDefault("APP_NAME", "forwarder");
        int port = Integer.parseInt(System.getenv().getOrDefault("APP_PORT", "8081"));
        String next = System.getenv().getOrDefault("NEXT_URL", "");

        System.out.println("[" + name + "] Intermediário na porta " + port);
        System.out.println("[" + name + "] Próximo nó: " + next);

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/event", new Handler(name, next));
        server.start();

        System.out.println("[" + name + "] Aguardando requisições...");
    }

   
    static class Handler implements HttpHandler {
        private final String name;
        private final String next;

        public Handler(String name, String next) {
            this.name = name;
            this.next = next;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("[" + name + "] Recebido: " + body + " @ " + LocalDateTime.now());

            if (next != null && !next.isBlank()) {
                URL url = new URL(next);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.connect();
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(bytes);
                }
                int status = conn.getResponseCode();
                System.out.println("[" + name + "] Encaminhado → status HTTP " + status);
                conn.disconnect();
            }

            byte[] resp = "OK".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, resp.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(resp);
            }
        }
    }
}
