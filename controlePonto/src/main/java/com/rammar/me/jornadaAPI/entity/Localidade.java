package com.rammar.me.jornadaAPI.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Localidade {
    @Id
    private Long id;
    private String descricao;
    @ManyToOne
    private NivelAcesso nivelAcesso;
}
