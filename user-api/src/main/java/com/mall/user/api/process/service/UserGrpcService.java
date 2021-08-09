package com.mall.user.api.process.service;

import com.google.protobuf.Descriptors;
import com.grpc.lib.GetUserByEmailRequest;
import com.grpc.lib.GetUserByIdRequest;
import com.grpc.lib.UserResponse;
import com.grpc.lib.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(UserGrpcService.class);

    @Override
    public void findById(GetUserByIdRequest request, StreamObserver<UserResponse> responseObserver) {

        StringBuffer sb = new StringBuffer();
        Map<Descriptors.FieldDescriptor, Object> map = request.getAllFields();
        Iterator<Descriptors.FieldDescriptor> it = map.keySet().iterator();
        while(it.hasNext()) {
            Descriptors.FieldDescriptor fd = it.next();
            sb.append("Request " + fd.getJsonName() + "=" + map.get(fd) + "\n" );
        }
        logger.info("request => {}", sb);

        UserResponse userResponse = UserResponse.newBuilder()
                .setEmail("kyo@gmail.com")
                .setFullName("김규호")
                .setId("1")
                .setId("test2")
                .build();

        //unary라 onNext 1회만 호출 가능 //데이터 전송
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void findByEmail(GetUserByEmailRequest request, StreamObserver<UserResponse> responseObserver) {

        StringBuffer sb = new StringBuffer();
        Map<Descriptors.FieldDescriptor, Object> map = request.getAllFields();
        Iterator<Descriptors.FieldDescriptor> it = map.keySet().iterator();
        while(it.hasNext()) {
            Descriptors.FieldDescriptor fd = it.next();
            sb.append("Request " + fd.getJsonName() + "=" + map.get(fd) + "\n" );
        }
        logger.info("request => {}", sb);

        UserResponse userResponse = UserResponse.newBuilder()
                .setEmail("kyo@gmail.com")
                .setFullName("김규호22")
                .setId("1")
                .setPassword("test")
                .build();

        //unary라 onNext 1회만 호출 가능 //데이터 전송
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();

    }


}