package com.rammar.me.controlePonto.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class BancoHoras {

    @EqualsAndHashCode
    @Embeddable
    @Data
    public class BancoHorasId implements Serializable{
        private long idBancoHoras;
        private long idMovimentacao;
        private long idUsuario;
    }

    @EmbeddedId
    private BancoHorasId bancoHorasId;
    private LocalDateTime dataTrabalhada;
    private BigDecimal quantidadeHoras;
    private BigDecimal saldoHoras;

}
