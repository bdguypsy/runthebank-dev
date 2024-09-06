package com.github.runthebank.dto;

import com.github.runthebank.enums.StatusContaEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDto {
    private Long idConta;
    @NotNull
    private String agenciaConta;
    @NotNull
    private BigDecimal saldoConta;
    @NotNull
    private StatusContaEnum statusConta;
    @NotNull
    private Long idCliente;
}