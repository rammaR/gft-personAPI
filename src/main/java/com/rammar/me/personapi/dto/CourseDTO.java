package com.rammar.me.personapi.dto;

import com.rammar.me.personapi.entity.Student;
import com.rammar.me.personapi.entity.Teacher;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class CourseDTO {

    @NotEmpty
    private Long id;

    @Size(min = 2, max = 100, message = "Campo name do curso deve ter no mínimo 2 e no máximo 100 caracteres.")
    private String name;

    @NotEmpty
    @Valid
    private Teacher teacher;

    @Valid
    private Set<Student> students;

}
