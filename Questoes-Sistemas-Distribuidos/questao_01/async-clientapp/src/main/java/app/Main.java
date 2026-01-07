package app;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;



/**
 * Nó cliente HTTP responsável por gerar mensagens e enviá-las
 * para o nó de envio (sender) através de requisições POST.
 *
 * Papel na arquitetura da questão 1:
 * - Produz eventos com um identificador único e timestamp.
 * - Envia as mensagens para o serviço HTTP configurado na variável TARGET_URL.
 *
 * Estados principais (variáveis):
 * - APP_NAME: nome lógico do nó, usado apenas para logs.
 * - TARGET_URL: URL do endpoint HTTP do sender.
 *
 * Comportamento principal:
 * - main(): entra em um laço gerando mensagens e chamando o método send().
 * - send(): abre uma conexão HTTP e publica o corpo da mensagem.
 */
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
