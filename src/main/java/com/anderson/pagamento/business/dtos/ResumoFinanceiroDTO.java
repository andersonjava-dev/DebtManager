package com.anderson.pagamento.business.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumoFinanceiroDTO {

    List<DividaDTO> dividas;
    List<PagamentoDTO>  pagamentos;


}
