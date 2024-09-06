package com.github.runthebank.controller;

import com.github.runthebank.dto.ContaDto;
import com.github.runthebank.service.ContaService;
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
@RequestMapping("/public/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaDto> salvarConta(@Valid @RequestBody ContaDto contaDto) {

        ContaDto contaDtoSalva = this.contaService.salvarConta(contaDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(contaDtoSalva);

    }

}