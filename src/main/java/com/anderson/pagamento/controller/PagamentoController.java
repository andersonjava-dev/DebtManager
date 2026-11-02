package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.business.service.PagamentoService;
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
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @PostMapping("/divida/{id}")
    public ResponseEntity<PagamentoDTO> registrarPagamento(@RequestBody BigDecimal valorPagamento,
                                                           @PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.registrarPagamento(valorPagamento, id));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        return ResponseEntity.ok(pagamentoService.listarPagamento());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorId(id));
    }

    @GetMapping("/divida")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorDivida(@RequestParam("id") Long dividaId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentoPorDivida(dividaId));
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentosPorCliente(@RequestParam("id") Long clienteId) {
        return ResponseEntity.ok(pagamentoService.listarPagamentosPorCliente(clienteId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentoPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorPeriodo(dataInicial, dataFinal));

    }

}



