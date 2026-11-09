package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.business.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento", description = "Gerenciamento de pagamentos, permitindo cadastrar e consultar pagamentos." )
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping("/divida/{id}")
    @Operation(summary = "Registrar pagamento", description = "Registrar pagamento de uma dívida.")
    @ApiResponse(responseCode = "200", description = "Pagamento registrado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<PagamentoDTO> registrarPagamento(@RequestBody BigDecimal valorPagamento,
                                                           @PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.registrarPagamento(valorPagamento, id));
    }

    @GetMapping
    @Operation(summary = "Listar pagamentos", description = "Retornar lista de pagamentos.")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarPagamento());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pagamento por ID", description = "Buscar pagamento específico por ID.")
    @ApiResponse(responseCode = "200", description = "Pagamento retornado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorId(id));
    }

    @GetMapping("/divida")
    @Operation(summary = "Listar pagamento por dívida", description = "Listar pagamento por divida")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorDivida(@RequestParam("id") Long dividaId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentoPorDivida(dividaId));
    }

    @GetMapping("/cliente")
    @Operation(summary = "Buscar pagamentos por cliente", description = "Retornar todos os pagamentos de um cliente.")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorCliente(@RequestParam("id") Long clienteId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentosPorCliente(clienteId));
    }

    @GetMapping("/período")
    @Operation(summary = "Buscar pagamentos por período ", description = "Buscar todos os pagamentos por um determinado período.")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentoPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorPeriodo(dataInicial, dataFinal));

    }

}



