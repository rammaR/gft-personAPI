package com.rammar.me.personapi.mapper;

import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.rammar.me.personapi.utils.PersonUtils.createFakePerson;
import static com.rammar.me.personapi.utils.PersonUtils.createFakePersonDTO;

public class PersonMapperTest {

    @Test
    void testConversionPersonModelToPersonDTO() {
        Person person = createFakePerson();
        PersonDTO expectedPersonDTO = createFakePersonDTO();
        //
        PersonDTO convertedPersonDTO = PersonMapper.personMapper.toDTO(person);
        //
        Assert.assertEquals(expectedPersonDTO, convertedPersonDTO);
    }

    @Test
    void testConversionPersonDTOToPersonModel(){
        PersonDTO personDTO = createFakePersonDTO();
        Person person = createFakePerson();
        //
        Person convertedPerson = PersonMapper.personMapper.toModel(personDTO);
        //
        Assert.assertEquals(person, convertedPerson);
    }

}
