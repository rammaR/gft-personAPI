package com.rammar.me.personapi.controller;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public MessageDTO postBook(@RequestBody Person person) {
        Person saved = this.repository.save(person);
        return MessageDTO.builder().message("Saved person id: " + saved.getId()).build();
    }

}
