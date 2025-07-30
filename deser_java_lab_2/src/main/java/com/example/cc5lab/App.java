package com.example.cc5lab;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
  public static void main(String[] args) { SpringApplication.run(App.class, args); }
    // debug
  @Bean
  ApplicationRunner checkJar() {
    return args -> System.out.println("[+] commons-collections from: " +
      org.apache.commons.collections.Transformer.class
        .getProtectionDomain().getCodeSource().getLocation());
  }
}
