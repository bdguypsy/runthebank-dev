package com.github.runthebank.model;

import com.github.runthebank.enums.StatusContaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CONTA")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConta;
    @NotNull
    private String agenciaConta;
    @NotNull
    private BigDecimal saldoConta;
    @NotNull
    private StatusContaEnum statusConta;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente clienteConta;
}