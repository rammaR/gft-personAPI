package com.rammar.me.jornadaAPI.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Audited
public class Empresa {

    @Id
    private Long id;
    private String descricao;
    private String cnpj;
    //@OneToOne private Address address;
}
