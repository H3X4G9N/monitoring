package com.datalogging.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}
