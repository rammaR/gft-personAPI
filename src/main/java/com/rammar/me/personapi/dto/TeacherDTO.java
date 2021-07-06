package com.rammar.me.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO extends PersonDTO {


    @NotEmpty
    private Long registration;

}
