package com.anderson.pagamento.business.service;

import com.anderson.pagamento.business.converter.PagamentoConverter;
import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.infrastructure.entity.Divida;
import com.anderson.pagamento.infrastructure.entity.Pagamento;
import com.anderson.pagamento.infrastructure.enums.Status;
import com.anderson.pagamento.infrastructure.exception.ResourceNotFoundException;
import com.anderson.pagamento.infrastructure.repository.DividaRepository;
import com.anderson.pagamento.infrastructure.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PagamentoConverter pagamentoConverter;
    private final DividaRepository dividaRepository;

    public PagamentoDTO registrarPagamento(BigDecimal valorPagamento, Long id) {

        Divida divida = dividaRepository.findById(id).orElseThrow(()
        -> new ResourceNotFoundException("Divida não encontrada, id invalido: "+ id));

        if(valorPagamento.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("O pagamento deve ser maior zero");
        }
        if(valorPagamento.compareTo(divida.getSaldoRestante()) > 0){
            throw new RuntimeException("Pagamento maior que o saldo da dívida");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(valorPagamento);
        pagamento.setDivida(divida);
        pagamento.setDataPagamento(LocalDateTime.now());

        divida.setSaldoRestante(
                divida.getSaldoRestante().subtract(valorPagamento)
        );
        if (divida.getSaldoRestante().compareTo(BigDecimal.ZERO) == 0) {
            divida.setStatus((Status.PAGA));
        }else {
            divida.setStatus(Status.PARCIALMENTE_PAGA);
        }
        pagamentoRepository.save(pagamento);

        return pagamentoConverter.paraPagamentoDTO(pagamento);



    }

    public List<PagamentoDTO> listarPagamento() {
        return pagamentoRepository.findAll()
                .stream()
                .map(pagamentoConverter::paraPagamentoDTO)
                .toList();
    }

    public PagamentoDTO buscarPagamentoPorId(Long id) {
        Pagamento entity = pagamentoRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Id inválido, pagamento não enocontrado"));
        return pagamentoConverter.paraPagamentoDTO(entity);
    }

    public List<PagamentoDTO> listarPagamentoPorDivida(Long dividaId) {
        return pagamentoRepository.findByDividaId(dividaId)
                .stream()
                .map(pagamentoConverter::paraPagamentoDTO)
                .toList();
    }

    public List<PagamentoDTO> listarPagamentosPorCliente(Long clienteId) {
        return pagamentoRepository.findByDividaClienteId(clienteId)
                .stream()
                .map(pagamentoConverter::paraPagamentoDTO)
                .toList();

    }

    public List<PagamentoDTO> buscarPagamentoPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return pagamentoRepository.findByDataPagamentoBetween(dataInicial, dataFinal)
                .stream()
                .map(pagamentoConverter::paraPagamentoDTO)
                .toList();


    }
}
