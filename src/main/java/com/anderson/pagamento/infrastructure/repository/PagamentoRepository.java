package com.anderson.pagamento.infrastructure.repository;

import com.anderson.pagamento.infrastructure.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByDividaId(Long id);

    List<Pagamento> findByDividaClienteId(Long id);

    List<Pagamento> findByDataPagamentoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

}