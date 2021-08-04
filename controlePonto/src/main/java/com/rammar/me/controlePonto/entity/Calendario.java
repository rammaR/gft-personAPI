package com.rammar.me.controlePonto.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Calendario {
    @Id
    private Long id;
    private String descricao;
    @ManyToOne
    private TipoData tipoData;
    private LocalDateTime dataEspecial;
}
