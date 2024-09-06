package com.github.runthebank.service;

import com.github.runthebank.dto.PagamentoDto;
import com.github.runthebank.enums.StatusContaEnum;
import com.github.runthebank.enums.TipoPagamentoEnum;
import com.github.runthebank.model.Conta;
import com.github.runthebank.model.Pagamento;
import com.github.runthebank.repository.ContaRepository;
import com.github.runthebank.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagamentoDto reverterPagamento(Long idPagamento) throws Exception {

        Optional<Pagamento> pagamentoOptional = this.pagamentoRepository.findById(idPagamento);

        if (pagamentoOptional.isPresent()
                && pagamentoOptional.get().getTipoPagamento() != TipoPagamentoEnum.REVERTIDO)
        {
            Optional<Conta> contaDebitoOptional = this.contaRepository.findById(pagamentoOptional.get().getContaDebitoPagamento().getIdConta());
            Optional<Conta> contaCreditoOptional = this.contaRepository.findById(pagamentoOptional.get().getContaCreditoPagamento().getIdConta());

            if (contaDebitoOptional.isPresent() && contaCreditoOptional.isPresent()) {
                if (contaDebitoOptional.get().getStatusConta() == StatusContaEnum.INATIVA
                        || contaCreditoOptional.get().getStatusConta() == StatusContaEnum.INATIVA) {
                    throw new Exception("Uma ou todas as contas informadas se encontra(m) inativada(s)!");
                }

                if (contaCreditoOptional.get().getSaldoConta()
                        .compareTo(pagamentoOptional.get().getValorPagamento()) < 0) {
                    throw new Exception("A conta de débito do pagamento não possui saldo suficiente para a transação!");
                }

                Conta contaDebito = contaDebitoOptional.get();
                Conta contaCredito = contaCreditoOptional.get();
                contaDebito.setSaldoConta(contaDebito.getSaldoConta().add(pagamentoOptional.get().getValorPagamento()));
                contaCredito.setSaldoConta(contaCredito.getSaldoConta().subtract(pagamentoOptional.get().getValorPagamento()));

                this.contaRepository.save(contaDebito);
                this.contaRepository.save(contaCredito);

                Pagamento pagamento = this.modelMapper.map(pagamentoOptional.get(), Pagamento.class);
                pagamento.setTipoPagamento(TipoPagamentoEnum.REVERTIDO);
                pagamento = this.pagamentoRepository.save(pagamento);

                return this.modelMapper.map(pagamento, PagamentoDto.class);
            } else {
                throw new Exception("Contas não localizadas na base de dados!");
            }
        } else {
            throw new Exception("Pagamento não localizado na base de dados!");
        }

    }

    @Override
    public PagamentoDto realizarPagamento(PagamentoDto pagamentoDto) throws Exception {

        Optional<Conta> contaDebitoOptional = this.contaRepository.findById(pagamentoDto.getIdContaDebitoPagamento());
        Optional<Conta> contaCreditoOptional = this.contaRepository.findById(pagamentoDto.getIdContaCreditoPagamento());

        if (contaDebitoOptional.isPresent() && contaCreditoOptional.isPresent()) {
            if (contaDebitoOptional.get().getStatusConta() == StatusContaEnum.INATIVA
                    || contaCreditoOptional.get().getStatusConta() == StatusContaEnum.INATIVA) {
                throw new Exception("Uma ou todas as contas informadas se encontra(m) inativada(s)!");
            }

            if (contaDebitoOptional.get().getSaldoConta()
                    .compareTo(pagamentoDto.getValorPagamento()) < 0) {
                throw new Exception("A conta de débito do pagamento não possui saldo suficiente para a transação!");
            }

            Conta contaDebito = contaDebitoOptional.get();
            Conta contaCredito = contaCreditoOptional.get();
            contaDebito.setSaldoConta(contaDebito.getSaldoConta().subtract(pagamentoDto.getValorPagamento()));
            contaCredito.setSaldoConta(contaCredito.getSaldoConta().add(pagamentoDto.getValorPagamento()));

            this.contaRepository.save(contaDebito);
            this.contaRepository.save(contaCredito);

            Pagamento pagamento = this.modelMapper.map(pagamentoDto, Pagamento.class);
            pagamento = this.pagamentoRepository.save(pagamento);

            return this.modelMapper.map(pagamento, PagamentoDto.class);
        } else {
            throw new Exception("Contas não localizadas na base de dados!");
        }

    }

}