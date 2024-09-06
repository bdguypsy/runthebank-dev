package com.github.runthebank.controller;

import com.github.runthebank.dto.ClienteDto;
import com.github.runthebank.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> salvarCliente(@Valid @RequestBody ClienteDto clienteDto) throws Exception {

        ClienteDto clienteDtoSalvo = this.clienteService.salvarCliente(clienteDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDtoSalvo);

    }

}