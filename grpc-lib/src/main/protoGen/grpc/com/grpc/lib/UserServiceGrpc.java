package com.grpc.lib;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.33.1)",
    comments = "Source: user-service.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.grpc.lib.GetUserByIdRequest,
      com.grpc.lib.UserResponse> getFindByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findById",
      requestType = com.grpc.lib.GetUserByIdRequest.class,
      responseType = com.grpc.lib.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.lib.GetUserByIdRequest,
      com.grpc.lib.UserResponse> getFindByIdMethod() {
    io.grpc.MethodDescriptor<com.grpc.lib.GetUserByIdRequest, com.grpc.lib.UserResponse> getFindByIdMethod;
    if ((getFindByIdMethod = UserServiceGrpc.getFindByIdMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindByIdMethod = UserServiceGrpc.getFindByIdMethod) == null) {
          UserServiceGrpc.getFindByIdMethod = getFindByIdMethod =
              io.grpc.MethodDescriptor.<com.grpc.lib.GetUserByIdRequest, com.grpc.lib.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.GetUserByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findById"))
              .build();
        }
      }
    }
    return getFindByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.lib.GetUserByEmailRequest,
      com.grpc.lib.UserResponse> getFindByEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findByEmail",
      requestType = com.grpc.lib.GetUserByEmailRequest.class,
      responseType = com.grpc.lib.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.lib.GetUserByEmailRequest,
      com.grpc.lib.UserResponse> getFindByEmailMethod() {
    io.grpc.MethodDescriptor<com.grpc.lib.GetUserByEmailRequest, com.grpc.lib.UserResponse> getFindByEmailMethod;
    if ((getFindByEmailMethod = UserServiceGrpc.getFindByEmailMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getFindByEmailMethod = UserServiceGrpc.getFindByEmailMethod) == null) {
          UserServiceGrpc.getFindByEmailMethod = getFindByEmailMethod =
              io.grpc.MethodDescriptor.<com.grpc.lib.GetUserByEmailRequest, com.grpc.lib.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findByEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.GetUserByEmailRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("findByEmail"))
              .build();
        }
      }
    }
    return getFindByEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.lib.CreateUserRequest,
      com.grpc.lib.UserResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = com.grpc.lib.CreateUserRequest.class,
      responseType = com.grpc.lib.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.lib.CreateUserRequest,
      com.grpc.lib.UserResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<com.grpc.lib.CreateUserRequest, com.grpc.lib.UserResponse> getCreateMethod;
    if ((getCreateMethod = UserServiceGrpc.getCreateMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getCreateMethod = UserServiceGrpc.getCreateMethod) == null) {
          UserServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<com.grpc.lib.CreateUserRequest, com.grpc.lib.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.CreateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.lib.UpdateUserRequest,
      com.grpc.lib.UserResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = com.grpc.lib.UpdateUserRequest.class,
      responseType = com.grpc.lib.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.lib.UpdateUserRequest,
      com.grpc.lib.UserResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<com.grpc.lib.UpdateUserRequest, com.grpc.lib.UserResponse> getUpdateMethod;
    if ((getUpdateMethod = UserServiceGrpc.getUpdateMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getUpdateMethod = UserServiceGrpc.getUpdateMethod) == null) {
          UserServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<com.grpc.lib.UpdateUserRequest, com.grpc.lib.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.UpdateUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.grpc.lib.DeleteUserRequest,
      com.grpc.lib.UserResponse> getDeleteByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteById",
      requestType = com.grpc.lib.DeleteUserRequest.class,
      responseType = com.grpc.lib.UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.grpc.lib.DeleteUserRequest,
      com.grpc.lib.UserResponse> getDeleteByIdMethod() {
    io.grpc.MethodDescriptor<com.grpc.lib.DeleteUserRequest, com.grpc.lib.UserResponse> getDeleteByIdMethod;
    if ((getDeleteByIdMethod = UserServiceGrpc.getDeleteByIdMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getDeleteByIdMethod = UserServiceGrpc.getDeleteByIdMethod) == null) {
          UserServiceGrpc.getDeleteByIdMethod = getDeleteByIdMethod =
              io.grpc.MethodDescriptor.<com.grpc.lib.DeleteUserRequest, com.grpc.lib.UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.DeleteUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.grpc.lib.UserResponse.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("deleteById"))
              .build();
        }
      }
    }
    return getDeleteByIdMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void findById(com.grpc.lib.GetUserByIdRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFindByIdMethod(), responseObserver);
    }

    /**
     */
    public void findByEmail(com.grpc.lib.GetUserByEmailRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getFindByEmailMethod(), responseObserver);
    }

    /**
     */
    public void create(com.grpc.lib.CreateUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void update(com.grpc.lib.UpdateUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    public void deleteById(com.grpc.lib.DeleteUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteByIdMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFindByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.lib.GetUserByIdRequest,
                com.grpc.lib.UserResponse>(
                  this, METHODID_FIND_BY_ID)))
          .addMethod(
            getFindByEmailMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.lib.GetUserByEmailRequest,
                com.grpc.lib.UserResponse>(
                  this, METHODID_FIND_BY_EMAIL)))
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.lib.CreateUserRequest,
                com.grpc.lib.UserResponse>(
                  this, METHODID_CREATE)))
          .addMethod(
            getUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.lib.UpdateUserRequest,
                com.grpc.lib.UserResponse>(
                  this, METHODID_UPDATE)))
          .addMethod(
            getDeleteByIdMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.lib.DeleteUserRequest,
                com.grpc.lib.UserResponse>(
                  this, METHODID_DELETE_BY_ID)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void findById(com.grpc.lib.GetUserByIdRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findByEmail(com.grpc.lib.GetUserByEmailRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFindByEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(com.grpc.lib.CreateUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(com.grpc.lib.UpdateUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteById(com.grpc.lib.DeleteUserRequest request,
        io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteByIdMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.grpc.lib.UserResponse findById(com.grpc.lib.GetUserByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.lib.UserResponse findByEmail(com.grpc.lib.GetUserByEmailRequest request) {
      return blockingUnaryCall(
          getChannel(), getFindByEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.lib.UserResponse create(com.grpc.lib.CreateUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.lib.UserResponse update(com.grpc.lib.UpdateUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.grpc.lib.UserResponse deleteById(com.grpc.lib.DeleteUserRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteByIdMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.lib.UserResponse> findById(
        com.grpc.lib.GetUserByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.lib.UserResponse> findByEmail(
        com.grpc.lib.GetUserByEmailRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getFindByEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.lib.UserResponse> create(
        com.grpc.lib.CreateUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.lib.UserResponse> update(
        com.grpc.lib.UpdateUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.lib.UserResponse> deleteById(
        com.grpc.lib.DeleteUserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteByIdMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_BY_ID = 0;
  private static final int METHODID_FIND_BY_EMAIL = 1;
  private static final int METHODID_CREATE = 2;
  private static final int METHODID_UPDATE = 3;
  private static final int METHODID_DELETE_BY_ID = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_BY_ID:
          serviceImpl.findById((com.grpc.lib.GetUserByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse>) responseObserver);
          break;
        case METHODID_FIND_BY_EMAIL:
          serviceImpl.findByEmail((com.grpc.lib.GetUserByEmailRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((com.grpc.lib.CreateUserRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((com.grpc.lib.UpdateUserRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse>) responseObserver);
          break;
        case METHODID_DELETE_BY_ID:
          serviceImpl.deleteById((com.grpc.lib.DeleteUserRequest) request,
              (io.grpc.stub.StreamObserver<com.grpc.lib.UserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.lib.UserServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getFindByIdMethod())
              .addMethod(getFindByEmailMethod())
              .addMethod(getCreateMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getDeleteByIdMethod())
              .build();
        }
      }
    }
    return result;
  }
}
