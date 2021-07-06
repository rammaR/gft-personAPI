package com.rammar.me.personapi.controller;

import com.google.common.base.Preconditions;
import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.dto.StudentDTO;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Student")
@RestController
@RequestMapping("/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {

    private StudentService studentService;

    @ApiOperation(value = "Salva um objeto do tipo Student")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO postStudent(@RequestBody @Valid StudentDTO studentDTO) {
        Preconditions.checkNotNull(studentDTO);
        return studentService.saveStudent(studentDTO);
    }

    @ApiOperation(value = "Retorna lista de estudantes cadastrados")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PersonDTO> getStudents(){
        return studentService.listAll();
    }

    @ApiOperation(value = "Retorna um objeto do tipo StudentDTO")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PersonDTO getStudent(@PathVariable Long id) throws PersonNotFoundException {
        return studentService.findById(id);
    }

    @ApiOperation(value = "Deleta registro de um estudante dado um ID")
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageDTO deleteStudent(@PathVariable Long id) throws PersonNotFoundException {
        return studentService.delete(id);
    }

    @ApiOperation(value = "Atualiza uma pessoa dado um ID")
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MessageDTO updateStudent(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        Preconditions.checkNotNull(personDTO);
        return studentService.update(id, personDTO);
    }

}
