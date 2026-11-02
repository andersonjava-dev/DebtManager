package com.anderson.pagamento.infrastructure.entity;

import com.anderson.pagamento.infrastructure.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private BigDecimal valorTotal;
    @Column(name = "saldo_restante")
    private BigDecimal saldoRestante;
    @Column(name = "dataDaCompra", nullable = false, length = 8)
    private LocalDateTime dataDaCompra;
    @Column(name = "statusDePagamento", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "divida")
    private List<Pagamento> pagamentos;
}
