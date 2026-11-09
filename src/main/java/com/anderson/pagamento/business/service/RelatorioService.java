package com.anderson.pagamento.business.service;

import com.anderson.pagamento.business.converter.ClienteConverter;
import com.anderson.pagamento.business.converter.PagamentoConverter;
import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.business.dtos.ResumoFinanceiroDTODois;
import com.anderson.pagamento.infrastructure.entity.Divida;
import com.anderson.pagamento.infrastructure.entity.Pagamento;
import com.anderson.pagamento.infrastructure.repository.ClienteRepository;
import com.anderson.pagamento.infrastructure.repository.DividaRepository;
import com.anderson.pagamento.infrastructure.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ClienteRepository clienteRepository;
    private final DividaRepository dividaRepository;
    private final ClienteConverter clienteConverter;
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoConverter pagamentoConverter;


    public List<ClienteDTO> listarDevedores() {
        List<Divida> dividasAbertas = dividaRepository.findBySaldoRestanteGreaterThan(BigDecimal.ZERO);
        return dividasAbertas.stream()
                .map(Divida::getCliente)
                .distinct()
                .map(clienteConverter::paraClienteDTO)
                .toList();
    }

    public BigDecimal totalEmAberto() {
        return dividaRepository.findBySaldoRestanteGreaterThan(BigDecimal.ZERO)
                .stream()
                .map(Divida::getSaldoRestante)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal totalRecebido() {
        return pagamentoRepository.findAll()
                .stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public Long quantidadeDevedores() {
        return clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getDividas().stream()
                        .anyMatch(divida -> divida.getSaldoRestante()
                                .compareTo(BigDecimal.ZERO) > 0))
                .count();

    }

    public List<PagamentoDTO> buscarPagamentoPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return pagamentoRepository.findByDataPagamentoBetween(dataInicial, dataFinal)
                .stream()
                .map(pagamentoConverter::paraPagamentoDTO)
                .toList();
    }

    public ResumoFinanceiroDTODois consultarResumoFinanceiro() {
        // totalReceber
        BigDecimal totalReceber = dividaRepository.findBySaldoRestanteGreaterThan(BigDecimal.ZERO)
                .stream()
                .map(Divida::getSaldoRestante)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // totalDividas
        BigDecimal totalDividas = (BigDecimal) dividaRepository.findAll()
                .stream()
                .map(Divida::getSaldoRestante)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        //totalRecebido
        BigDecimal totalRecebido = pagamentoRepository.findAll()
                .stream()
                .map(Pagamento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        //quantidadeClienteDevedor
        long totalDevedores = clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getDividas().stream()
                        .anyMatch(divida -> divida.getSaldoRestante()
                                .compareTo(BigDecimal.ZERO) > 0))
                .count();
        return new ResumoFinanceiroDTODois(totalReceber, totalDividas, totalRecebido, totalDevedores);


        // quantidadeClienteDevedor
    }
}
