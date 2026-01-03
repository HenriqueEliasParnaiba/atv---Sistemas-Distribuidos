package app;

import br.edu.ifpb.grpc.Ack;
import br.edu.ifpb.grpc.FluxoEventosGrpc;
import br.edu.ifpb.grpc.Mensagem;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(System.getenv().getOrDefault("GRPC_PORT", "50051"));
        Server server = ServerBuilder.forPort(port)
                .addService(new Service())
                .build()
                .start();

        System.out.println("[grpc-server] ON " + port);
        server.awaitTermination();
    }

    static class Service extends FluxoEventosGrpc.FluxoEventosImplBase {

        @Override
        public StreamObserver<Mensagem> enviar(StreamObserver<Ack> responseObserver) {
            return new StreamObserver<>() {
                @Override
                public void onNext(Mensagem mensagem) {
                    System.out.println("[grpc-server] " + mensagem.getConteudo() + " @" + LocalDateTime.now());
                    Ack ack = Ack.newBuilder()
                            .setId(mensagem.getId())
                            .setStatus("OK")
                            .build();
                    responseObserver.onNext(ack);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("[grpc-server] Erro: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("[grpc-server] Stream concluído.");
                    responseObserver.onCompleted();
                }
            };
        }
    }
}
