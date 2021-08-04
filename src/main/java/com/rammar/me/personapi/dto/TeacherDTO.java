package com.rammar.me.personapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Data
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class TeacherDTO extends PersonDTO {


    @NotEmpty
    private Long registration;

}
