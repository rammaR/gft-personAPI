package com.rammar.me.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Campo firstname deve ter no mínimo 2 e no máximo 100 caracteres.")
    private String firstname;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Campo lastname deve ter no mínimo 2 e no máximo 100 caracteres.")
    private String lastname;

    @NotEmpty
    private String cpf;

    private String birthdate;

    @NotEmpty
    @Valid
    private List<PhoneDTO> phone;

}
