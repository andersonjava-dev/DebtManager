package com.anderson.pagamento.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
    @Column(name = "dataPagamento", nullable = false)
    private LocalDateTime dataPagamento;


    @ManyToOne
    @JoinColumn(name = "divida_id")
    private Divida divida;


}
