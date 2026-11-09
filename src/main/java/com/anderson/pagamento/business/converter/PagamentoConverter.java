package com.anderson.pagamento.business.converter;

import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.infrastructure.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConverter {

    public PagamentoDTO paraPagamentoDTO(Pagamento entity) {
        return PagamentoDTO.builder()
                .id(entity.getId())
                .dataPagamento(entity.getDataPagamento())
                .valor(entity.getValor())
                .build();
    }

    public Pagamento paraPagamentoEntity(PagamentoDTO pagamentoDTO) {
        return Pagamento.builder()
                .id(pagamentoDTO.getId())
                .valor(pagamentoDTO.getValor())
                .dataPagamento(pagamentoDTO.getDataPagamento())
                .build();
    }
}