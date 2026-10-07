package com.anderson.pagamento.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

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
    @Column(name = "nome", nullable = false, length = 20)
    private String nome;
    @Column(name = "email", nullable = false, length = 55, unique = true)
    private String email;
    @Column(name = "senha", nullable = false, length = 55)
    private String senha;
    @Column(name = "descricao", nullable = false, length = 255)
    private String deescricao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    List<Divida> dividas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    List<Pagamento>  pagamentos;
}
