package com.rammar.me.personapi.service;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.mapper.PersonMapper;
import com.rammar.me.personapi.repository.PersonRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.rammar.me.personapi.utils.PersonUtils.createFakePerson;
import static com.rammar.me.personapi.utils.PersonUtils.createFakePersonDTO;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void TestGivenPersonReturnSavedMessage() {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Person savedPerson = createFakePerson();
        Mockito.lenient().when(personMapper.toModel(fakePersonDTO)).thenReturn(savedPerson);
        Mockito.lenient().when(personRepository.save(savedPerson)).thenReturn(savedPerson);
        //
        MessageDTO messageExpected = getMessageExpected(savedPerson);
        MessageDTO messageReturned = personService.savePerson(fakePersonDTO);
        //
        assertEquals(messageExpected, messageReturned);
    }

    @Test
    void testGivenValidPersonIdThenReturnThisPerson() throws PersonNotFoundException {
        //Arrange
        PersonDTO expectedPersonDTO = createFakePersonDTO();
        Person savedPerson = createFakePerson();
        Mockito.lenient().when(personRepository.findById(savedPerson.getId())).thenReturn(Optional.of(savedPerson));
        Mockito.lenient().when(personMapper.toDTO(savedPerson)).thenReturn(expectedPersonDTO);
        //Action
        PersonDTO testPersonDTO = personService.findById(savedPerson.getId());
        //Assertion
        assertEquals(expectedPersonDTO, testPersonDTO);
        assertEquals(expectedPersonDTO.getId(), testPersonDTO.getId());
        assertEquals(expectedPersonDTO.getFirstname(), testPersonDTO.getFirstname());
    }

    @Test
    void testGivenInvalidPersonIdThenThrowException() {
        var invalidPersonId = 0l;
        Mockito.lenient().when(personRepository.findById(invalidPersonId)).thenReturn(Optional.ofNullable(any(Person.class)));
        assertThrows(PersonNotFoundException.class, () -> {
            personService.findById(invalidPersonId);
        });
    }

    @Test
    void testGivenNoDataThenReturnAllPeopleRegistered(){
        List<Person> expectedPeople = new ArrayList<>();
        PersonDTO personDTO = createFakePersonDTO();
        expectedPeople.add(createFakePerson());
        Mockito.lenient().when(personRepository.findAll()).thenReturn(expectedPeople);
        Mockito.lenient().when(personMapper.toDTO(any(Person.class))).thenReturn(personDTO);
        //
        List<PersonDTO> actualPeople = personService.listAll();
        //
        assertFalse(actualPeople.isEmpty());
        assertEquals(actualPeople.get(0).getId(), personDTO.getId());
    }

    @Test
    void testUpdateAPerson() throws PersonNotFoundException {
        //
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Person savedPerson = createFakePerson();
        Mockito.lenient().when(personRepository.findById(savedPerson.getId())).thenReturn(Optional.of(savedPerson));
        Mockito.lenient().when(personRepository.save(savedPerson)).thenReturn(savedPerson);
        Mockito.lenient().when(personMapper.toModel(fakePersonDTO)).thenReturn(savedPerson);
        //
        MessageDTO messageExpected = getMessageExpected(savedPerson);
        MessageDTO messageReturned = personService.update(savedPerson.getId(), fakePersonDTO);
        //
        assertEquals(messageExpected, messageReturned);
    }

    @Test
    void testInvalidIdAndUpdatePersonThrowsException(){
        PersonDTO fakePersonDto = createFakePersonDTO();
        Long invalidId = 9l;
        fakePersonDto.setId(invalidId);
        fakePersonDto.setFirstname("Test Person");

        Mockito.lenient().when(personRepository.findById(invalidId)).thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> {
            personService.update(invalidId, fakePersonDto);
        });
    }

    @Test
    void testGivenValidPersonIdThenReturnSuccesOnDelete() throws PersonNotFoundException {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Person personToDelete = createFakePerson();
        MessageDTO expectedMessage = MessageDTO.builder().message("Person " + fakePersonDTO.getId() + " deleted.").build();
        when(personRepository.findById(fakePersonDTO.getId())).thenReturn(Optional.of(personToDelete));
        //
        MessageDTO actualMessage = personService.delete(fakePersonDTO.getId());
        //
        verify(personRepository, times(1)).deleteById(fakePersonDTO.getId());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGivenInvalidPersonIdThenReturnSuccesOnDelete(){
        PersonDTO fakePersonDto = createFakePersonDTO();
        Long invalidId = 9l;
        fakePersonDto.setId(invalidId);

        Mockito.lenient().when(personRepository.findById(invalidId)).thenReturn(Optional.ofNullable(any(Person.class)));

        assertThrows(PersonNotFoundException.class, () -> {
            personService.delete(invalidId);
        });
    }

    private MessageDTO getMessageExpected(Person savedPerson) {
        return MessageDTO.builder().message("Saved person with ID: " + savedPerson.getId()).build();
    }

}
