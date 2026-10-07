package com.anderson.pagamento.business.dtos;

import com.anderson.pagamento.infrastructure.enums.Status;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DividaDTO {

    private Long id;
    private String descricaoDaCompra;
    private String valorDaDivida;
    private LocalDateTime dataDaCompra;
    private Status status;
}
