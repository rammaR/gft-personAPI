package com.rammar.me.beerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.rammar.me.beerapi"})
public class BeerapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerapiApplication.class, args);
    }

}
