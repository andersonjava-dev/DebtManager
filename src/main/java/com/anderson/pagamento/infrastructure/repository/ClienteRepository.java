package com.anderson.pagamento.infrastructure.repository;

import com.anderson.pagamento.infrastructure.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    Cliente findByNome(String nome);




    




}
