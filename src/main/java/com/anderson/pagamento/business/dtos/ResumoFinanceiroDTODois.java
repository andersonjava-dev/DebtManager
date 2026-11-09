package com.anderson.pagamento.business.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumoFinanceiroDTODois {

private BigDecimal totalReceber;
private BigDecimal totalDivida;
private BigDecimal totalRecebido;
private long totalDevedores;
}
