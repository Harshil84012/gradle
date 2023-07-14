package com.sungjun.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.sungjun.*")
@EnableJpaRepositories("com.sungjun.*")
@ComponentScan("com.sungjun.*")
@EnableJpaAuditing

public class SampleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApiApplication.class, args);
    }

}