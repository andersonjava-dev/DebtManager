package com.anderson.pagamento.infrastructure.repository;

import com.anderson.pagamento.infrastructure.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
