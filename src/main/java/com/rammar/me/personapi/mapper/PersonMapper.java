package com.rammar.me.personapi.mapper;

import com.rammar.me.personapi.dto.PersonDTO;
import com.rammar.me.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "birthdate", target = "birthdate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);

}
