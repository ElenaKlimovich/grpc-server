package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 *  GRPC - Server
 */
public class ServerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetMessageServiceImpl())
                .build();

        server.start();

        System.out.println("Grpc Server started successfully!..");

        server.awaitTermination();
    }
}
