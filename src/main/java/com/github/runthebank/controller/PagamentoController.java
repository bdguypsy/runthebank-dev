package com.github.runthebank.controller;

import com.github.runthebank.dto.PagamentoDto;
import com.github.runthebank.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/pagamento")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoDto> realizarPagamento(@Valid @RequestBody PagamentoDto pagamentoDto) throws Exception {

        PagamentoDto pagamentoDtoSalvo = this.pagamentoService.realizarPagamento(pagamentoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoDtoSalvo);

    }

    @PatchMapping("/{idPagamento}")
    public ResponseEntity<PagamentoDto> reverterPagamento(@PathVariable(value = "idPagamento") Long idPagamento) throws Exception {

        PagamentoDto pagamentoDto = this.pagamentoService.reverterPagamento(idPagamento);

        return ResponseEntity.ok(pagamentoDto);

    }

}