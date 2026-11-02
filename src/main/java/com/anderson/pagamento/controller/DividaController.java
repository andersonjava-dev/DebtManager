package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.DividaDTO;
import com.anderson.pagamento.business.service.DividaService;
import com.anderson.pagamento.infrastructure.repository.DividaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/divida")
public class DividaController {

    private final DividaService dividaService;


    @PostMapping
    public ResponseEntity<DividaDTO> cadastrarDivida(@RequestBody DividaDTO dto,
                                                     @RequestParam Long id) {
        return ResponseEntity.ok(dividaService.cadastrarDivida(id, dto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DividaDTO>> listarDividaCadastradas() {
        return ResponseEntity.ok(dividaService.listarDividasCadastradas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DividaDTO> buscarDividaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(dividaService.buscarDividaPorId(id));
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<DividaDTO>> buscarDividaPorCliente(@RequestParam("id") Long id) {
        return ResponseEntity.ok(dividaService.listarDividaPorCliente(id));
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<DividaDTO>> listarDividasAbertas() {
        return ResponseEntity.ok(dividaService.listarDividasAberta());
    }

    @GetMapping("/pagas")
    public ResponseEntity<List<DividaDTO>> listarDividasPagas() {
        return ResponseEntity.ok(dividaService.listarDividasPagas());
    }
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> deletarDividas(@PathVariable Long id){
        dividaService.deletarDivida(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DividaDTO> atualizarDivida(@RequestBody DividaDTO dto,
                                                     @PathVariable Long id) {
        return ResponseEntity.ok(dividaService.atualizarDivida(dto, id));
    }
}
