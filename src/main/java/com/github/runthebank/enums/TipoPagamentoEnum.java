package com.github.runthebank.enums;

import lombok.Getter;

@Getter
public enum TipoPagamentoEnum {
    EFETUADO("Efetuado"),
    REVERTIDO("Revertido");

    private final String descricao;

    TipoPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }
}