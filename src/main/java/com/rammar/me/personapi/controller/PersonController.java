package com.rammar.me.personapi.controller;

import com.google.common.base.Preconditions;
import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.exceptions.MyResourceNotFoundException;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.service.PersonService;
import com.rammar.me.personapi.utils.RestPreconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Person")
@RestController
@RequestMapping("/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @ApiOperation(value = "Salva um objeto do tipo Person")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO postPerson(@RequestBody @Valid PersonDTO personDTO) {
        Preconditions.checkNotNull(personDTO);
        return personService.savePerson(personDTO);
    }

    @ApiOperation(value = "Retorna lista de objetos do tipo Person")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> getPeople() {
        return personService.listAll();
    }

    @ApiOperation(value = "Retorna um objeto do tipo Person")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonDTO getPerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @ApiOperation(value = "Deleta registro de uma pessoa dado um ID")
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDTO deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        return personService.delete(id);
    }

    @ApiOperation(value = "Atualiza uma pessoa dado um ID")
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MessageDTO updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        Preconditions.checkNotNull(personDTO);
        return personService.update(id, personDTO);
    }
}
