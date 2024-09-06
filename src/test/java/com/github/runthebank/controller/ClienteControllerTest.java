package com.github.runthebank.controller;

import com.github.runthebank.dto.ClienteDto;
import com.github.runthebank.service.ClienteService;
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
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;
    @Mock
    private ClienteDto clienteDto;
    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void salvarCliente() throws Exception {

        when(this.clienteService.salvarCliente(this.clienteDto)).thenReturn(this.clienteDto);
        ResponseEntity<ClienteDto> responseEntity = this.clienteController.salvarCliente(this.clienteDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

}