package br.edu.ifpb.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: mensagens.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FluxoEventosGrpc {

  private FluxoEventosGrpc() {}

  public static final java.lang.String SERVICE_NAME = "FluxoEventos";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<br.edu.ifpb.grpc.Mensagem,
      br.edu.ifpb.grpc.Ack> getEnviarMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Enviar",
      requestType = br.edu.ifpb.grpc.Mensagem.class,
      responseType = br.edu.ifpb.grpc.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<br.edu.ifpb.grpc.Mensagem,
      br.edu.ifpb.grpc.Ack> getEnviarMethod() {
    io.grpc.MethodDescriptor<br.edu.ifpb.grpc.Mensagem, br.edu.ifpb.grpc.Ack> getEnviarMethod;
    if ((getEnviarMethod = FluxoEventosGrpc.getEnviarMethod) == null) {
      synchronized (FluxoEventosGrpc.class) {
        if ((getEnviarMethod = FluxoEventosGrpc.getEnviarMethod) == null) {
          FluxoEventosGrpc.getEnviarMethod = getEnviarMethod =
              io.grpc.MethodDescriptor.<br.edu.ifpb.grpc.Mensagem, br.edu.ifpb.grpc.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Enviar"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.edu.ifpb.grpc.Mensagem.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.edu.ifpb.grpc.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new FluxoEventosMethodDescriptorSupplier("Enviar"))
              .build();
        }
      }
    }
    return getEnviarMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FluxoEventosStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FluxoEventosStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FluxoEventosStub>() {
        @java.lang.Override
        public FluxoEventosStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FluxoEventosStub(channel, callOptions);
        }
      };
    return FluxoEventosStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FluxoEventosBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FluxoEventosBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FluxoEventosBlockingStub>() {
        @java.lang.Override
        public FluxoEventosBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FluxoEventosBlockingStub(channel, callOptions);
        }
      };
    return FluxoEventosBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FluxoEventosFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FluxoEventosFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FluxoEventosFutureStub>() {
        @java.lang.Override
        public FluxoEventosFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FluxoEventosFutureStub(channel, callOptions);
        }
      };
    return FluxoEventosFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<br.edu.ifpb.grpc.Mensagem> enviar(
        io.grpc.stub.StreamObserver<br.edu.ifpb.grpc.Ack> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getEnviarMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FluxoEventos.
   */
  public static abstract class FluxoEventosImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FluxoEventosGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FluxoEventos.
   */
  public static final class FluxoEventosStub
      extends io.grpc.stub.AbstractAsyncStub<FluxoEventosStub> {
    private FluxoEventosStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FluxoEventosStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FluxoEventosStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<br.edu.ifpb.grpc.Mensagem> enviar(
        io.grpc.stub.StreamObserver<br.edu.ifpb.grpc.Ack> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getEnviarMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FluxoEventos.
   */
  public static final class FluxoEventosBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FluxoEventosBlockingStub> {
    private FluxoEventosBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FluxoEventosBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FluxoEventosBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FluxoEventos.
   */
  public static final class FluxoEventosFutureStub
      extends io.grpc.stub.AbstractFutureStub<FluxoEventosFutureStub> {
    private FluxoEventosFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FluxoEventosFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FluxoEventosFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_ENVIAR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ENVIAR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.enviar(
              (io.grpc.stub.StreamObserver<br.edu.ifpb.grpc.Ack>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getEnviarMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              br.edu.ifpb.grpc.Mensagem,
              br.edu.ifpb.grpc.Ack>(
                service, METHODID_ENVIAR)))
        .build();
  }

  private static abstract class FluxoEventosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FluxoEventosBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.edu.ifpb.grpc.MensagensProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FluxoEventos");
    }
  }

  private static final class FluxoEventosFileDescriptorSupplier
      extends FluxoEventosBaseDescriptorSupplier {
    FluxoEventosFileDescriptorSupplier() {}
  }

  private static final class FluxoEventosMethodDescriptorSupplier
      extends FluxoEventosBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FluxoEventosMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FluxoEventosGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FluxoEventosFileDescriptorSupplier())
              .addMethod(getEnviarMethod())
              .build();
        }
      }
    }
    return result;
  }
}
