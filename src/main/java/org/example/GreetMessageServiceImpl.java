package org.example;

import com.example.grpc.GreetMessageServiceGrpc;
import com.example.grpc.MessageService;
import io.grpc.stub.StreamObserver;


public class GreetMessageServiceImpl extends GreetMessageServiceGrpc.GreetMessageServiceImplBase {


    @Override
    public void formMassGreetMessage(MessageService.GreetMessageRequest request,
                                 StreamObserver<MessageService.GreetMessageResponse> responseObserver) {

        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String message = i + " : Hello, "
                    + request.getUserName() + " "
                    + request.getUserSurname() + "! Welcome!";
            MessageService.GreetMessageResponse response = MessageService.GreetMessageResponse.newBuilder()
                    .setReplyMessage(message)
                    .build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }


    // without streaming response
    @Override
    public void formGreetMessage(MessageService.GreetMessageRequest request,
                                 StreamObserver<MessageService.GreetMessageResponse> responseObserver) {

        MessageService.GreetMessageResponse response = MessageService.GreetMessageResponse.newBuilder()
                .setReplyMessage("Good morning, "
                        + request.getUserName() + " "
                        + request.getUserSurname() + "! Glad to see you!")
                .build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
