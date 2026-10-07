package com.anderson.pagamento.business.converter;

import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.DividaDTO;
import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.infrastructure.entity.Cliente;
import com.anderson.pagamento.infrastructure.entity.Divida;
import com.anderson.pagamento.infrastructure.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteConverter {

    public Cliente paraCliente(ClienteDTO dto) {
        return Cliente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .dividas(paraListaDivida(dto.getDividas()))
                .pagamentos(paraListaPagamento(dto.getPagamentos()))
                .build();
    }

    public List<Divida> paraListaDivida(List<DividaDTO> dividaDTO) {
        return dividaDTO.stream().map(this::paraDivida).toList();
    }

    public Divida paraDivida(DividaDTO dto) {
        return Divida.builder()
                .status(dto.getStatus())
                .valorDaDivida(dto.getValorDaDivida())
                .dataDaCompra(dto.getDataDaCompra())
                .descricaoDaCompra(dto.getDescricaoDaCompra())
                .id(dto.getId())
                .build();
    }

    public List<Pagamento> paraListaPagamento(List<PagamentoDTO> pagamentoDTO) {
        return pagamentoDTO.stream().map(this::paraPagamento).toList();

    }

    public Pagamento paraPagamento(PagamentoDTO pagamentoDTO) {
        return Pagamento.builder()
                .id(pagamentoDTO.getId())
                .valor(pagamentoDTO.getValor())
                .dataPagamento(pagamentoDTO.getDataPagamento())
                .build();
    }

    public ClienteDTO paraClienteDTO(Cliente entity) {
        return ClienteDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .dividas(paraListaDividaDTO(entity.getDividas()))
                .pagamentos(paraListaPagamentoDTO(entity.getPagamentos()))
                .build();
    }

    public List<DividaDTO> paraListaDividaDTO(List<Divida> dividaEntity) {
        return dividaEntity.stream().map(this::paraDividaDTO).toList();
    }

    public DividaDTO paraDividaDTO(Divida entity) {
        return DividaDTO.builder()
                .id(entity.getId())
                .valorDaDivida(entity.getValorDaDivida())
                .dataDaCompra(entity.getDataDaCompra())
                .descricaoDaCompra(entity.getDescricaoDaCompra())
                .status(entity.getStatus())
                .build();
    }

    public List<PagamentoDTO> paraListaPagamentoDTO(List<Pagamento> pagamentoEntity) {
        return pagamentoEntity.stream().map(this::paraPagamentoDTO).toList();
    }

    public PagamentoDTO paraPagamentoDTO(Pagamento entity) {
        return PagamentoDTO.builder()
                .id(entity.getId())
                .dataPagamento(entity.getDataPagamento())
                .valor(entity.getValor())
                .build();


    }
}
