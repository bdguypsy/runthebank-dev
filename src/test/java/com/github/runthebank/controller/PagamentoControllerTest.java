package com.github.runthebank.controller;

import com.github.runthebank.dto.PagamentoDto;
import com.github.runthebank.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagamentoControllerTest {

    @Mock
    private PagamentoService pagamentoService;
    @Mock
    private PagamentoDto pagamentoDto;
    @InjectMocks
    private PagamentoController pagamentoController;

    @Test
    public void realizarPagamento() throws Exception {

        when(this.pagamentoService.realizarPagamento(this.pagamentoDto)).thenReturn(this.pagamentoDto);
        ResponseEntity<PagamentoDto> responseEntity = this.pagamentoController.realizarPagamento(this.pagamentoDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void reverterPagamento() throws Exception {

        when(this.pagamentoService.reverterPagamento(1L)).thenReturn(this.pagamentoDto);
        ResponseEntity<PagamentoDto> responseEntity = this.pagamentoController.reverterPagamento(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

}