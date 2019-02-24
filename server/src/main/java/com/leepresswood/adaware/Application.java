package com.leepresswood.adaware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}