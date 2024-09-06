package com.github.runthebank.dto;

import com.github.runthebank.enums.TipoClienteEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long idCliente;
    @NotNull
    private String nomeCliente;
    @NotNull
    private String cpfCnpjCliente;
    @NotNull
    private TipoClienteEnum tipoCliente;
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