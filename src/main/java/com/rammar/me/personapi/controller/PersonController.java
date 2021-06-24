package com.rammar.me.personapi.controller;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.repository.PersonRepository;
import com.rammar.me.personapi.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@Api(value = "Person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService service) {
        this.personService = service;
    }

    @ApiOperation(value = "Salva um objeto do tipo Person")
    @PostMapping(value = "/person", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO postPerson(@RequestBody @Valid PersonDTO person) {
        return personService.savePerson(person);
    }

    @ApiOperation(value = "Retorna lista de objetos do tipo Person")
    @GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> getPeople() {
        return personService.listAll();
    }

    @ApiOperation(value = "Retorna um objeto do tipo Person")
    @GetMapping(value = "/person/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonDTO getPerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @ApiOperation(value = "Deleta registro de uma pessoa dado um ID")
    @GetMapping(value = "/person/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDTO deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.delete(id);
    }

    @ApiOperation(value = "Atualiza uma pessoa dado um ID")
    @GetMapping(value = "/person/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MessageDTO updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.update(id, personDTO);
    }
}
