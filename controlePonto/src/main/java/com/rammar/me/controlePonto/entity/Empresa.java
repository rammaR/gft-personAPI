package com.rammar.me.controlePonto.entity;

import com.rammar.me.personapi.entity.Address;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Empresa {

    @Id
    private Long id;
    private String descricao;
    private String cnpj;
    //@OneToOne private Address address;
}
