package com.anderson.pagamento.business.converter;

import com.anderson.pagamento.business.dtos.DividaDTO;
import com.anderson.pagamento.infrastructure.entity.Divida;
import org.springframework.stereotype.Component;

@Component
public class DividaConverter {


    public DividaDTO paraDividaDTO(Divida entity) {
        return DividaDTO.builder()
                .id(entity.getId())
                .dataDaCompra(entity.getDataDaCompra())
                .descricaoDaCompra(entity.getDescricaoDaCompra())
                .valorTotal(entity.getValorTotal())
                .saldoRestante(entity.getSaldoRestante())
                .status(entity.getStatus())
                .build();
    }

    public Divida paraDividaEntity(DividaDTO dto) {
        return Divida.builder()
                .id(dto.getId())
                .dataDaCompra(dto.getDataDaCompra())
                .descricaoDaCompra(dto.getDescricaoDaCompra())
                .saldoRestante(dto.getSaldoRestante())
                .status(dto.getStatus())
                .valorTotal(dto.getValorTotal())
                .build();
    }

    public Divida updateDivida(DividaDTO dto, Divida entity) {
        return Divida.builder()

                .id(entity.getId())
                .dataDaCompra(dto.getDataDaCompra() != null ? dto.getDataDaCompra() : entity.getDataDaCompra())
                .descricaoDaCompra(dto.getDescricaoDaCompra() != null ? dto.getDescricaoDaCompra() : entity.getDescricaoDaCompra())
                .valorTotal(dto.getValorTotal() != null ? dto.getValorTotal() : entity.getValorTotal())
                .saldoRestante(dto.getSaldoRestante() != null ? dto.getSaldoRestante() : entity.getSaldoRestante())
                .status(dto.getStatus() != null ? dto.getStatus() : entity.getStatus())
                .build();
    }
}
