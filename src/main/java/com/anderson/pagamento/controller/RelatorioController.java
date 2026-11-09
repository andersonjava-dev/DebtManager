package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.business.dtos.ResumoFinanceiroDTODois;
import com.anderson.pagamento.business.service.RelatorioService;
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
@RequestMapping("/relatorio")
@Tag(name = "Relatório", description ="Resumo financeiro do comércio" )
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/devedores")
    @Operation(summary = "Listar devedores", description = "Retornar todos os clientes que possuem saldo devedor.")
    @ApiResponse(responseCode = "200", description = "Clientes devedores retornados com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<ClienteDTO>> listarDevedores() {
        return ResponseEntity.ok(relatorioService.listarDevedores());
    }

    @GetMapping("/total-em-aberto")
    @Operation(summary = "Consultar total a receber ", description = "Retornar o valor total das dívidas pendentes que o comércio tem a receber dos clientes.")
    @ApiResponse(responseCode = "200", description = "Total a receber retornado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<BigDecimal> consultarTotalEmAberto() {
        return ResponseEntity.ok(relatorioService.totalEmAberto());
    }

    @GetMapping("/total-recebido")
    @Operation(summary = "Consultar total recebido", description = "Retornar valor total recebido.")
    @ApiResponse(responseCode = "200", description = "Dívida cadastrada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<BigDecimal> consultarTotalRecebido() {
        return ResponseEntity.ok(relatorioService.totalRecebido());
    }

    @GetMapping("/quantidade-devedores")
    @Operation(summary = "Consultar quantidade de devedores", description = "Consulltar quantidade de clientes devedores.")
    @ApiResponse(responseCode = "200", description = "Quantidade de devedores retornado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<Long> consultarQuatidadeDevedores() {
        return ResponseEntity.ok(relatorioService.quantidadeDevedores());
    }

    @GetMapping("/pagamentos")
    @Operation(summary = "Buscar pagamento por período", description = "Retornar uma lista de pagamentos por período.")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<PagamentoDTO>> buscarPagamentoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok((relatorioService.buscarPagamentoPorPeriodo(dataInicial, dataFinal)));
    }

    @GetMapping("/resumo")
    @Operation(summary = "Consultar resumo financeiro", description = "Retormar total a receber, total de dívidas, total recebido e total de devedores.")
    @ApiResponse(responseCode = "200", description = "Resumo financeiro retornado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<ResumoFinanceiroDTODois> consultarResumoFinanceiro(){
        return ResponseEntity.ok(relatorioService.consultarResumoFinanceiro());
    }


}
