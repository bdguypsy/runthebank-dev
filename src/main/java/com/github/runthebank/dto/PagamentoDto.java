package com.github.runthebank.dto;

import com.github.runthebank.enums.TipoPagamentoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDto {
    private Long idPagamento;
    @NotNull
    private BigDecimal valorPagamento;
    @NotNull
    private TipoPagamentoEnum tipoPagamento;
    @NotNull
    private Long idContaDebitoPagamento;
    @NotNull
    private Long idContaCreditoPagamento;
}