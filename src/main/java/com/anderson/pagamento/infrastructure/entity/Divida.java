package com.anderson.pagamento.infrastructure.entity;

import com.anderson.pagamento.infrastructure.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.BindParam;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "divida")
@Builder
public class Divida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricaoDaCompra", nullable = false, length = 255)
    private String descricaoDaCompra;
    @Column(name = "valorDaCompra", nullable = false)
    private String valorDaDivida;
    @Column(name = "dataDaCompra", nullable = false, length = 8)
    private LocalDateTime dataDaCompra;
    @Column(name = "statusDePagamento", nullable = false)
    private Status status;

}
