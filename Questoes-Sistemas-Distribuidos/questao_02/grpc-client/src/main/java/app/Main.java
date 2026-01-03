package app;

import br.edu.ifpb.grpc.Ack;
import br.edu.ifpb.grpc.FluxoEventosGrpc;
import br.edu.ifpb.grpc.Mensagem;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.UUID;

public class Main {

    public static void main(String[] args) throws Exception {
        String host = System.getenv().getOrDefault("GRPC_HOST", "localhost");
        int port = Integer.parseInt(System.getenv().getOrDefault("GRPC_PORT", "50051"));

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        FluxoEventosGrpc.FluxoEventosStub stub = FluxoEventosGrpc.newStub(channel);

        StreamObserver<Ack> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Ack ack) {
                System.out.println("[grpc-client] ACK " + ack.getId() +
                        " status=" + ack.getStatus());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("[grpc-client] Erro: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("[grpc-client] DONE");
            }
        };

        StreamObserver<Mensagem> requestObserver = stub.enviar(responseObserver);

        for (int i = 1; i <= 5; i++) {
            Mensagem mensagem = Mensagem.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setConteudo("msg#" + i + " @" + LocalDateTime.now())
                    .build();

            System.out.println("[grpc-client] Enviando: " + mensagem.getConteudo());
            requestObserver.onNext(mensagem);
            Thread.sleep(1000);
        }

        requestObserver.onCompleted();
        Thread.sleep(2000);
        channel.shutdown();
    }
}
