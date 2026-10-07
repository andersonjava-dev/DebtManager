package com.anderson.pagamento.business.dtos;

import com.anderson.pagamento.infrastructure.entity.Divida;
import com.anderson.pagamento.infrastructure.entity.Pagamento;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String deescricao;
    List<DividaDTO> dividas;
    List<PagamentoDTO>  pagamentos;

}
