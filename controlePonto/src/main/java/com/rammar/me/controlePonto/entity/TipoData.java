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
public class TipoData {
    @Id
    private long id;
    private String descricao;
}
