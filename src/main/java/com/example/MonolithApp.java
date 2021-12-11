package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MonolithApp {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    SpringApplication.run(MonolithApp.class, args);
  }
}
