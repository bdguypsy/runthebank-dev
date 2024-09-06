package com.github.runthebank.service;

import com.github.runthebank.dto.PagamentoDto;

public interface PagamentoService {
    PagamentoDto reverterPagamento(Long idPagamento) throws Exception;
    PagamentoDto realizarPagamento(PagamentoDto pagamentoDto) throws Exception;
}