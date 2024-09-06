package com.github.runthebank.enums;

import lombok.Getter;

@Getter
public enum TipoClienteEnum {
    PESSOA_FISICA("Pessoa Física - PF"),
    PESSOA_JURIDICA("Pessoa Jurídica - PJ");

    private final String descricao;

    TipoClienteEnum(String descricao) {
        this.descricao = descricao;
    }
}