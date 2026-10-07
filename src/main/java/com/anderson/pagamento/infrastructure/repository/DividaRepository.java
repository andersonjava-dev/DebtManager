package com.anderson.pagamento.infrastructure.repository;

import com.anderson.pagamento.infrastructure.entity.Divida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DividaRepository extends JpaRepository<Divida, Long> {
}
