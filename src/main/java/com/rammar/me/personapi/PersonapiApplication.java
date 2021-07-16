package com.rammar.me.personapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.rammar.me.personapi"})
public class PersonapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonapiApplication.class, args);
    }

}
