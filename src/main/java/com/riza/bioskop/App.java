package com.riza.bioskop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan(basePackages = { "com.riza.bioskop.repository" })
@SpringBootApplication(scanBasePackages = "com.riza.bioskop")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
