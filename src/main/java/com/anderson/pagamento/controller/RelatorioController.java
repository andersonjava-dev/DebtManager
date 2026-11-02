package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.business.dtos.ResumoFinanceiroDTODois;
import com.anderson.pagamento.business.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/devedores")
    public ResponseEntity<List<ClienteDTO>> listarDevedores() {
        return ResponseEntity.ok(relatorioService.listarDevedores());
    }

    @GetMapping("/total-em-aberto")
    public ResponseEntity<BigDecimal> consultarTotalEmAberto() {
        return ResponseEntity.ok(relatorioService.totalEmAberto());
    }

    @GetMapping("/total-recebido")
    public ResponseEntity<BigDecimal> consultarTotalRecebido() {
        return ResponseEntity.ok(relatorioService.totalRecebido());
    }

    @GetMapping("/quantidade-devedores")
    public ResponseEntity<Long> consultarQuatidadeDevedores() {
        return ResponseEntity.ok(relatorioService.quantidadeDevedores());
    }

    @GetMapping("/pagamentos")
    public ResponseEntity<List<PagamentoDTO>> buscarPagamentoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok((relatorioService.buscarPagamentoPorPeriodo(dataInicial, dataFinal)));
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroDTODois> consultarResumoFinanceiro(){
        return ResponseEntity.ok(relatorioService.consultarResumoFinanceiro());
    }


}
