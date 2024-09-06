package com.github.runthebank.enums;

import lombok.Getter;

@Getter
public enum StatusContaEnum {
    ATIVA("Ativa"),
    INATIVA("Inativa");

    private final String descricao;

    StatusContaEnum(String descricao) {
        this.descricao = descricao;
    }
}