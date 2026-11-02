package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.ResumoFinanceiroDTO;
import com.anderson.pagamento.business.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientesCadastrados());

    }

    @GetMapping("/nome")
    public ResponseEntity<ClienteDTO> listarClientePorNome(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(clienteService.listarClientePorNome(nome));
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO dto,
                                                       @RequestParam("id") Long id) {
        return ResponseEntity.ok(clienteService.atualizarCliente(dto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
        clienteService.deletaClientePorId(id);
        return ResponseEntity.ok()
                .build();
    }
    @GetMapping("/historico")
    public ResponseEntity<ResumoFinanceiroDTO> buscarHistoricoFinanceiro(){
        return ResponseEntity.ok(clienteService.buscarHistoricoFinanceiro());
    }


}



