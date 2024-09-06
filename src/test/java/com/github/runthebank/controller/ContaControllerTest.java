package com.github.runthebank.controller;

import com.github.runthebank.dto.ContaDto;
import com.github.runthebank.service.ContaService;
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
public class ContaControllerTest {

    @Mock
    private ContaService contaService;
    @Mock
    private ContaDto contaDto;
    @InjectMocks
    private ContaController contaController;

    @Test
    public void salvarConta() {

        when(this.contaService.salvarConta(this.contaDto)).thenReturn(this.contaDto);
        ResponseEntity<ContaDto> responseEntity = this.contaController.salvarConta(this.contaDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

}