package com.anderson.pagamento.infrastructure.repository;

import com.anderson.pagamento.infrastructure.entity.Divida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface DividaRepository extends JpaRepository<Divida, Long> {

  List<Divida> findByClienteId(Long id);

  List<Divida> findBySaldoRestanteGreaterThan(BigDecimal valor);

  List<Divida> findBySaldoRestante(BigDecimal valorRestante);





}
