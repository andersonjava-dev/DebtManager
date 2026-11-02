package com.anderson.pagamento.business.service;

import com.anderson.pagamento.business.converter.DividaConverter;
import com.anderson.pagamento.business.dtos.ClienteDTO;
import com.anderson.pagamento.business.dtos.DividaDTO;
import com.anderson.pagamento.infrastructure.entity.Cliente;
import com.anderson.pagamento.infrastructure.entity.Divida;
import com.anderson.pagamento.infrastructure.enums.Status;
import com.anderson.pagamento.infrastructure.exception.ResourceNotFoundException;
import com.anderson.pagamento.infrastructure.repository.ClienteRepository;
import com.anderson.pagamento.infrastructure.repository.DividaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DividaService {

    private final DividaRepository dividaRepository;
    private final DividaConverter dividaConverter;
    private final ClienteRepository clienteRepository;


    public DividaDTO cadastrarDivida(Long idCliente, DividaDTO dto) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(()
                -> new ResourceNotFoundException("Cliente não encontrado id inválido: " + idCliente));

        Divida entity = dividaConverter.paraDividaEntity(dto);
        entity.setCliente(cliente);
        entity.setStatus(Status.PENDENTE);
        entity.setSaldoRestante(dto.getSaldoRestante());
        entity.setDataDaCompra(LocalDateTime.now());

        return dividaConverter.paraDividaDTO(dividaRepository.save(entity));
    }

    public List<DividaDTO> listarDividasCadastradas() {
        return dividaRepository.findAll()
                .stream()
                .map(dividaConverter::paraDividaDTO)
                .toList();

    }

    public DividaDTO buscarDividaPorId(Long id) {
        Divida entity = dividaRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Id inválido" + id));
        return dividaConverter.paraDividaDTO(entity);
    }

    public List<DividaDTO> listarDividaPorCliente(Long id) {
        List<Divida> dividaList = dividaRepository.findByClienteId(id);
        return dividaList.stream()
                .map(dividaConverter::paraDividaDTO)
                .toList();


    }

    public List<DividaDTO> listarDividasAberta() {
        List<Divida> dividaList = dividaRepository.findBySaldoRestanteGreaterThan(BigDecimal.ZERO);

        return dividaList.stream()
                .map(dividaConverter::paraDividaDTO)
                .toList();
    }

    public List<DividaDTO> listarDividasPagas() {
        List<Divida> dividaList = dividaRepository.findBySaldoRestante(BigDecimal.ZERO);
        return dividaList.stream()
                .map(dividaConverter::paraDividaDTO)
                .toList();
    }

    public DividaDTO atualizarDivida(DividaDTO dto, Long id) {
        Divida entity = dividaRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Dívida não encontrada, id inválido " + id));

        Divida entit = dividaConverter.updateDivida(dto, entity);
        return dividaConverter.paraDividaDTO(dividaRepository.save(entit));
    }
    public void deletarDivida(Long id){
        dividaRepository.deleteById(id);
    }

}
