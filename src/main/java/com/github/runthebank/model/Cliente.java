package com.github.runthebank.model;

import com.github.runthebank.enums.TipoClienteEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CLIENTE", uniqueConstraints = {@UniqueConstraint(columnNames = {"cpfCnpjCliente"})})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @NotNull
    private String nomeCliente;
    @NotNull
    private String cpfCnpjCliente;
    @NotNull
    private TipoClienteEnum tipoCliente;
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private EnderecoCliente enderecoCliente;
}