package com.example.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }
}
