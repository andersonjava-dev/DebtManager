package com.anderson.pagamento.business.converter;

import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.infrastructure.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {


    public ClienteDTO paraClienteDTO(Cliente entity) {
        return ClienteDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .telefone(entity.getTelefone())
                .data_cadastro(entity.getData_cadastro())
                .build();
    }

    public Cliente paraClienteEntity(ClienteDTO dto) {
        return Cliente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .telefone(dto.getTelefone())
                .data_cadastro(dto.getData_cadastro())
                .build();

    }
    public Cliente updateCliente(Cliente entity, ClienteDTO dto){
        return Cliente.builder()
                .id(entity.getId())
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .telefone(dto.getTelefone() != null ? dto.getTelefone() : entity.getTelefone())
                .data_cadastro(dto.getData_cadastro() != null ? dto.getData_cadastro() : entity.getData_cadastro())
                .build();
    }
}


