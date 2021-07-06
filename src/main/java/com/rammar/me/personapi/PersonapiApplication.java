package com.rammar.me.personapi;

import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.repository.PersonRepository;
import com.rammar.me.personapi.service.PersonService;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class PersonapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonapiApplication.class, args);
    }

    /*
    @Bean
    ApplicationRunner init(PersonService personService) {
        PersonDTO[] data = {
                new PersonDTO(10l,"John","Doe", "", "12-12-1990", Arrays.asList()),
                new PersonDTO(20l,"Ramar","N. F.", "", "2012-12-12", Arrays.asList()),
                new PersonDTO(30l,"Pikachu","Eletric", "", "2012-12-12", Arrays.asList()),
                new PersonDTO(40l,"Dragon","Ball", "", "2012-12-12", Arrays.asList()),
                new PersonDTO(50l,"Seyia","Pegasus", "", "2012-12-12", Arrays.asList()),
        };

        return args -> {
            Stream.of(data).forEach(person -> {
                personService.savePerson(person);
            });

            personService.listAll().forEach(System.out::println);
        };
    }*/
}
