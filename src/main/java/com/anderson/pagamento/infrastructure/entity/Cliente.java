package com.anderson.pagamento.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
@Entity
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 20)
    private String nome;
    @Column(name = "telefone", length = 15, unique = true)
    private String telefone;
    @Column(name = "data_cadastro")
    private LocalDateTime data_cadastro;


    @OneToMany(mappedBy = "cliente")
    private List<Divida> dividas;
}
