package com.github.runthebank.service;

import com.github.runthebank.dto.ContaDto;
import com.github.runthebank.model.Conta;
import com.github.runthebank.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContaDto salvarConta(ContaDto contaDto) {

        Conta conta = this.modelMapper.map(contaDto, Conta.class);
        conta = this.contaRepository.save(conta);

        return this.modelMapper.map(conta, ContaDto.class);

    }

}