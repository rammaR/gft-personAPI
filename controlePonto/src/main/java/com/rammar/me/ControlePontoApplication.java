package com.rammar.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.rammar.me.jornadaAPI"})
public class ControlePontoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlePontoApplication.class, args);
    }

}
