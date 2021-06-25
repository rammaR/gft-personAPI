package com.rammar.me.personapi.utils;

import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import com.rammar.me.personapi.mapper.PersonMapper;

import java.util.Arrays;

import static com.rammar.me.personapi.utils.PhoneUtils.createFakePhoneDTO;

public class PersonUtils {

    public static PersonDTO createFakePersonDTO() {
        return PersonDTO.builder().id(999l).birthdate("02-02-2002").cpf("033").firstname("teste").lastname("teste").phone(Arrays.asList(createFakePhoneDTO())).build();
    }

    public static Person createFakePerson() {
        return PersonMapper.personMapper.toModel(createFakePersonDTO());
    }
}
