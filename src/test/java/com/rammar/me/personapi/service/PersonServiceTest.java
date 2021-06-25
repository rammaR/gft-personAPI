package com.rammar.me.personapi.service;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.entity.Phone;
import com.rammar.me.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static com.rammar.me.personapi.utils.PersonUtils.createFakePerson;
import static com.rammar.me.personapi.utils.PersonUtils.createFakePersonDTO;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void TestGivenPersonReturnSavedMessage() {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Person savedPerson = createFakePerson(); 
        Mockito.lenient().when(personRepository.save(savedPerson)).thenReturn(savedPerson);

        MessageDTO messageExpected = getMessageExpected(savedPerson);
        MessageDTO messageReturned = personService.savePerson(fakePersonDTO);

        Assertions.assertEquals(messageExpected, messageReturned);
    }

    private MessageDTO getMessageExpected(Person savedPerson) {
        return MessageDTO.builder().message("Saved person id: " + savedPerson.getId()).build();
    }

}
