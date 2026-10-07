package com.anderson.pagamento.business.dtos;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagamentoDTO {


    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
}
