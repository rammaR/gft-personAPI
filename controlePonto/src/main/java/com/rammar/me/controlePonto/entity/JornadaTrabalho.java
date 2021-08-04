package com.rammar.me.controlePonto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class JornadaTrabalho {
    @Id
    private Long id;
    private String descricao;

}
