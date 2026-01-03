package app;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;


public class Main {

        public static void main(String[] args) throws Exception {
        String name = System.getenv().getOrDefault("APP_NAME", "clientapp");
        String target = System.getenv().getOrDefault("TARGET_URL", "http://sender:8081/event");

        System.out.println("[" + name + "] Enviando mensagens para " + target);

        int count = 1;
        while (count <= 5) { // controla volume para facilitar testes
            String msg = "msg#" + count + " id=" + UUID.randomUUID() + " @ " + LocalDateTime.now();
            System.out.println("[" + name + "] Emitindo: " + msg);
            post(msg, target);
            Thread.sleep(3000);
            count++;
        }
    }

    
    private static void post(String body, String urlStr) throws Exception {
        URL url = new URL(urlStr);
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
        conn.getResponseCode();
        conn.disconnect();
    }
}
