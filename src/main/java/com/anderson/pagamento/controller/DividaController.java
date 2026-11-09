package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.DividaDTO;
import com.anderson.pagamento.business.service.DividaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/divida")
@Tag(name = "Dívidas", description = "Gerenciamento de dividas, permitindo cadastrar, consultar e atualizar registros.")

public class DividaController {

    private final DividaService dividaService;


    @PostMapping
    @Operation(summary = "Cadastrar dividas", description = "Cadastrar uma nova dívida de um cliente.")
    @ApiResponse(responseCode = "200", description = "Dívida cadastrada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<DividaDTO> cadastrarDivida(@RequestBody DividaDTO dto,
                                                     @RequestParam Long id) {
        return ResponseEntity.ok(dividaService.cadastrarDivida(id, dto));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar todas as dívidas", description = "Listar todas as dívidas.")
    @ApiResponse(responseCode = "200", description = "Dívidas listadas com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<DividaDTO>> listarDividaCadastradas() {
        return ResponseEntity.ok(dividaService.listarDividasCadastradas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar dívida por ID", description = "Buscar uma dívida específica com base no seu ID .")
    @ApiResponse(responseCode = "200", description = "Dívida encontrada e retornada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<DividaDTO> buscarDividaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(dividaService.buscarDividaPorId(id));
    }

    @GetMapping("/cliente")
    @Operation(summary = "Listar dívidas por cliente", description = "Retornar todas as dívidas vínculadas a um cliente específico, identificado pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Dívidas listadas com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<DividaDTO>> buscarDividaPorCliente(@RequestParam("id") Long id) {
        return ResponseEntity.ok(dividaService.listarDividaPorCliente(id));
    }

    @GetMapping("/abertas")
    @Operation(summary = "Listar dívidas abertas", description = "Retornar todas as dívidas com saldo devedor.")
    @ApiResponse(responseCode = "200", description = "Dívidas listadas com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<DividaDTO>> listarDividasAbertas() {
        return ResponseEntity.ok(dividaService.listarDividasAberta());
    }

    @GetMapping("/pagas")
    @Operation(summary = "Listar dívidas pagas", description = "Retornar todas as dívidas pagas.")
    @ApiResponse(responseCode = "200", description = "Dívidas pagas listadas com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<List<DividaDTO>> listarDividasPagas() {
        return ResponseEntity.ok(dividaService.listarDividasPagas());
    }
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> deletarDividas(@PathVariable Long id){
        dividaService.deletarDivida(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dívida", description = "Atualizar dívida por ID.")
    @ApiResponse(responseCode = "200", description = "Dívidas listadas com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<DividaDTO> atualizarDivida(@RequestBody DividaDTO dto,
                                                     @PathVariable Long id) {
        return ResponseEntity.ok(dividaService.atualizarDivida(dto, id));
    }
}
