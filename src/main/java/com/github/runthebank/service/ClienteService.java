package com.github.runthebank.service;

import com.github.runthebank.dto.ClienteDto;

public interface ClienteService {
    ClienteDto salvarCliente(ClienteDto clienteDto) throws Exception;
}