package com.anderson.pagamento.business.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private LocalDateTime data_cadastro;


}
