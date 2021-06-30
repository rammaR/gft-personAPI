package com.rammar.me.personapi.controller;

import com.rammar.me.personapi.dto.MessageDTO;
import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.exceptions.PersonNotFoundException;
import com.rammar.me.personapi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static com.rammar.me.personapi.utils.PersonUtils.createFakePersonDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    private static final String PEOPLE_API_URL_PATH = "/person";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Test
    public void testGivenValidPersonShouldSaveAndReturnMessage() throws Exception {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        MessageDTO fakeMessageDTO = MessageDTO.builder().message("Saved person with ID: " + fakePersonDTO.getId()).build();
        when(personService.savePerson(fakePersonDTO)).thenReturn(fakeMessageDTO);
        //
        this.mockMvc.perform(post(PEOPLE_API_URL_PATH, fakePersonDTO))
                .andDo(print())
                .andExpect(status().isCreated());

        PersonDTO expectedPersonDTO = createFakePersonDTO();
        MessageDTO expectedResponseMessage = MessageDTO.builder().message("Saved person with ID: " + fakePersonDTO.getId()).build();
    }

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Long validId = fakePersonDTO.getId();
        when(personService.findById(validId)).thenReturn(fakePersonDTO);
        this.mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + validId))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(999)))
                .andExpect(jsonPath("$.firstname", is(fakePersonDTO.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(fakePersonDTO.getLastname())));
    }

    @Test
    void testGivenInvalidIdShouldReturnNotFoundPersonException() throws Exception {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Long invalidID = 888l;
        fakePersonDTO.setId(invalidID);
        when(personService.findById(invalidID)).thenThrow(PersonNotFoundException.class);
        //
        mockMvc.perform(get(PEOPLE_API_URL_PATH + "/" + invalidID))
                .andExpect(status().isNotFound());
    }

    @Test
    void testWhenSendValidIDAndPersonThenShouldUpdate() throws Exception {
        PersonDTO expectedPersonDTO = createFakePersonDTO();
        MessageDTO expectedResponseMessage = MessageDTO.builder().message("Saved person with ID: " + expectedPersonDTO.getId()).build();
        Long expectedValidId = expectedPersonDTO.getId();
        when(personService.update(expectedValidId, expectedPersonDTO)).thenReturn(expectedResponseMessage);
        //
        mockMvc.perform(put(PEOPLE_API_URL_PATH + "/" + expectedValidId))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message", is(expectedResponseMessage.getMessage())));
    }

    @Test
    void testWhenDeleteIsCalledThenAPersonShouldBeDeleted() throws Exception {
        PersonDTO fakePersonDTO = createFakePersonDTO();
        Long validID = fakePersonDTO.getId();
        MessageDTO messageDTO = MessageDTO.builder().message("Person " + validID + " deleted.").build();
        when(personService.delete(validID)).thenReturn(messageDTO);
        //
        mockMvc.perform(delete(PEOPLE_API_URL_PATH + "/" + validID))
                .andExpect(status().isNoContent());
    }

}
