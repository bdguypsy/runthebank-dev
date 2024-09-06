package com.github.runthebank.model;

import com.github.runthebank.enums.TipoPagamentoEnum;
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
@Table(name = "TB_PAGAMENTO")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagamento;
    @NotNull
    private BigDecimal valorPagamento;
    @NotNull
    private TipoPagamentoEnum tipoPagamento;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaDebitoPagamento;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaCreditoPagamento;
}