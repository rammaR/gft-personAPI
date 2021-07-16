package com.rammar.me.personapi.service;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.mapper.PersonMapper;
import com.rammar.me.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rammar.me.personapi.mapper.PersonMapper.personMapper;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    //private PersonMapper personMapper;

    public MessageDTO savePerson(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);
        Person saved = personRepository.save(person);
        return MessageDTO.builder().message("Saved person with ID: " + saved.getId()).build();
    }

    public List<PersonDTO> listAll() {
        return personRepository.findAll().stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyById(id);
        return personMapper.toDTO(person);
    }

    public MessageDTO delete(Long id) throws PersonNotFoundException {
        verifyById(id);
        personRepository.deleteById(id);
        return MessageDTO.builder().message("Person " + id + " deleted.").build();
    }

    public MessageDTO update(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyById(id);
        Person toUpdate = personMapper.toModel(personDTO);
        this.personRepository.save(toUpdate);
        return MessageDTO.builder().message("Saved person with ID: " + id).build();
    }

    private Person verifyById(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }
}
