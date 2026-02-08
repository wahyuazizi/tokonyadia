package com.enigmacamp.tokonyadia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TokonyadiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokonyadiaApplication.class, args);
    }

}
