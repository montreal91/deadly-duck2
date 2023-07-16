package com.zwartzon;

import io.grpc.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class MainApp {
  public static void main(String[] args) {
    ApplicationContext ac = new AnnotationConfigApplicationContext(
        "com.zwartzon.infrastructure"
    );

    Server server = ac.getBean(Server.class);

    try {
      System.out.println("Starting 'Hello' gRPC server on port 8080.");
      server.start();
      server.awaitTermination();
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
      System.exit(64);
    }
  }
}
