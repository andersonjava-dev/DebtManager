package com.anderson.pagamento.business.service;

import com.anderson.pagamento.business.converter.ClienteConverter;
import com.anderson.pagamento.business.converter.DividaConverter;
import com.anderson.pagamento.business.converter.PagamentoConverter;
import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.DividaDTO;
import com.anderson.pagamento.business.dtos.PagamentoDTO;
import com.anderson.pagamento.business.dtos.ResumoFinanceiroDTO;
import com.anderson.pagamento.infrastructure.entity.Cliente;
import com.anderson.pagamento.infrastructure.entity.Divida;
import com.anderson.pagamento.infrastructure.entity.Pagamento;
import com.anderson.pagamento.infrastructure.exception.ResourceNotFoundException;
import com.anderson.pagamento.infrastructure.repository.ClienteRepository;
import com.anderson.pagamento.infrastructure.repository.DividaRepository;
import com.anderson.pagamento.infrastructure.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final DividaRepository dividaRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ClienteConverter clienteConverter;
    private final PagamentoConverter pagamentoConverter;
    private final DividaConverter dividaConverter;

    public ClienteDTO cadastrarCliente(ClienteDTO dto) {

        Cliente entity = clienteConverter.paraClienteEntity(dto);
        dto.getNome();
        dto.getTelefone();
        entity.setData_cadastro(LocalDateTime.now());
        return clienteConverter.paraClienteDTO(clienteRepository.save(entity));

    }

    public ClienteDTO buscarClientePorId(Long id) {
        Cliente entity = clienteRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Cliente não encontrada"));

        return clienteConverter.paraClienteDTO(entity);
    }

    public ClienteDTO atualizarCliente(ClienteDTO dto, Long id) {

        Cliente entity = clienteRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Cliente não encontrado, id inválido: " + id));

        Cliente cliente = clienteConverter.updateCliente(entity, dto);

        return clienteConverter.paraClienteDTO(clienteRepository.save(cliente));



    }

    public List<ClienteDTO> listarClientesCadastrados() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteConverter::paraClienteDTO)
                .toList();
    }

    public ClienteDTO listarClientePorNome(String nome) {
        return clienteConverter.paraClienteDTO(clienteRepository.findByNome(nome));
    }

    public void deletaClientePorId(Long id) {
        clienteRepository.deleteById(id);
    }

    public ResumoFinanceiroDTO buscarHistoricoFinanceiro() {
        List<PagamentoDTO> pagamentos = pagamentoRepository.findAll()
                .stream()
                .map(pagamentoConverter::paraPagamentoDTO)
                .toList();
        List<DividaDTO> dividas = dividaRepository.findAll()
                .stream()
                .map(dividaConverter::paraDividaDTO)
                .toList();
        return new ResumoFinanceiroDTO(dividas, pagamentos);

    }
}


