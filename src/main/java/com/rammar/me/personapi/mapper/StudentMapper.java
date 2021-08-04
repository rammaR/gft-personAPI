package com.rammar.me.personapi.mapper;

import com.rammar.me.personapi.dto.StudentDTO;
import com.rammar.me.personapi.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public interface StudentMapper {

    StudentMapper studentMapper = Mappers.getMapper( StudentMapper.class );

    Student toModel(StudentDTO dto);

    StudentDTO toDTO(Student dto);

}
