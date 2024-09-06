package com.github.runthebank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoClienteDto {
    private Long idEnderecoCliente;
    @NotNull
    private String logradouroEnderecoCliente;
    @NotNull
    private String numeroEnderecoCliente;
    private String complementoEnderecoCliente;
    @NotNull
    private String bairroEnderecoCliente;
    @NotNull
    private String cidadeEnderecoCliente;
    @NotNull
    private String ufEnderecoCliente;
}