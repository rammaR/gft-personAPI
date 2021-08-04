package com.rammar.me.controlePonto;

import com.rammar.me.personapi.entity.Address;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ControlePontoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlePontoApplication.class, args);
    }

}
