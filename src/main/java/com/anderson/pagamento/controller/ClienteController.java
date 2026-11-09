package com.anderson.pagamento.controller;

import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.ResumoFinanceiroDTO;
import com.anderson.pagamento.business.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
@Tag(name = "Clientes", description = "Gerenciamento de clientes, permitindo cadastrar, consultar, atualizar e excluir registros.")
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping
    @Operation(summary = "Cadastrar clientes", description = "Cadastrar um novo cliente.")
    @ApiResponse(responseCode = "200", description = "Cliente cadastrado.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Buscar e retornar os dados de um " +
                         "cliente especifico com base no seu ID.")
    @ApiResponse(responseCode ="200", description = "Cliente encontrado com sucesso.")
    @ApiResponse(responseCode = "400", description = "ID informado em formato inválido ou imcompatível com o tipo esperado.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarClientePorId(id));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar clientes", description = "Retornar a lista de clientes cadastrados no sistema.")
    @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientesCadastrados());

    }

    @GetMapping("/nome")
    @Operation(summary ="Buscar clientes por nome ", description = "Buscar e retornar clientes conforme o nome informado.")
    @ApiResponse(responseCode = "200", description = "Cliente listado por nome com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<ClienteDTO> listarClientePorNome(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(clienteService.listarClientePorNome(nome));
    }

    @PutMapping
    @Operation(summary = "Atualizar clientes", description = "Atualiazar dados do cliente.")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO dto,
                                                       @RequestParam("id") Long id) {
        return ResponseEntity.ok(clienteService.atualizarCliente(dto,id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar cliente por ID", description = "Deletar cliente por ID.")
    @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisição inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id) {
        clienteService.deletaClientePorId(id);
        return ResponseEntity.ok()
                .build();
    }
    @GetMapping("/{id}/historico")
    @Operation(summary = "Consultar histórico financeiro do cliente", description = "Retornar o histórico financeiro do cliente.")
    @ApiResponse(responseCode = "200", description = "Histórico encontrado e retornado comm sucesso.")
    @ApiResponse(responseCode = "400", description = "Requisoção inválida.")
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    public ResponseEntity<ResumoFinanceiroDTO> buscarHistoricoFinanceiro(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarHistoricoFinanceiro(id));
    }


}



