package com.rammar.me.personapi.utils;

import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;

import static com.rammar.me.personapi.utils.PhoneUtils.createFakePhone;
import static com.rammar.me.personapi.utils.PhoneUtils.createFakePhoneDTO;

public class PersonUtils {

    public static PersonDTO createFakePersonDTO() {
        return PersonDTO.builder().id(999l).birthdate("02-02-2012").cpf("033").firstname("teste").lastname("teste").phone(Arrays.asList(createFakePhoneDTO())).build();
    }

    public static Person createFakePerson() {
        return Person.builder().id(999l).birthdate(LocalDate.of(2012, 02, 02)).cpf("033").firstname("teste").lastname("teste").phone(Arrays.asList(createFakePhone())).build();
    }
}
