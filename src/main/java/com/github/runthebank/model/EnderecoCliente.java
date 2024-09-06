package com.github.runthebank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ENDERECO_CLIENTE")
public class EnderecoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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