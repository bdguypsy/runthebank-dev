package com.github.runthebank.service;

import com.github.runthebank.dto.ClienteDto;
import com.github.runthebank.model.Cliente;
import com.github.runthebank.model.EnderecoCliente;
import com.github.runthebank.repository.ClienteRepository;
import com.github.runthebank.repository.EnderecoClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClienteDto salvarCliente(ClienteDto clienteDto) throws Exception {

        if (!isCpf(clienteDto.getCpfCnpjCliente())
                && !isCnpj(clienteDto.getCpfCnpjCliente())
        ) {
            throw new Exception("CPF/CNPJ inválido!");
        }

        Optional<Cliente> verificarCpfCnpjCadastrado = this.clienteRepository.findByCpfCnpjCliente(clienteDto.getCpfCnpjCliente());

        if (verificarCpfCnpjCadastrado.isPresent()) {
            throw new Exception("CPF/CNPJ já cadastrado anteriormente!");
        }

        EnderecoCliente enderecoCliente = this.modelMapper.map(clienteDto, EnderecoCliente.class);
        enderecoCliente = this.enderecoClienteRepository.save(enderecoCliente);

        clienteDto.setIdEnderecoCliente(enderecoCliente.getIdEnderecoCliente());
        Cliente cliente = this.modelMapper.map(clienteDto, Cliente.class);
        cliente = this.clienteRepository.save(cliente);

        return this.modelMapper.map(cliente, ClienteDto.class);

    }

    private boolean isCpf(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try{
            Long.parseLong(cpf);
        } catch(NumberFormatException e){
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.parseInt(cpf.substring(nCount - 1, nCount));

            // multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4
            // e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            // para o segundo digito repita o procedimento incluindo o primeiro
            // digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        // Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
        // menos o resultado anterior.
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        // Segundo resto da divisão por 11.
        resto = (d2 % 11);

        // Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11
        // menos o resultado anterior.
        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        // Digito verificador do CPF que está sendo validado.
        String nDigVerific = cpf.substring(cpf.length() - 2);

        // Concatenando o primeiro resto com o segundo.
        nDigResult = digito1 + String.valueOf(digito2);

        // comparar o digito verificador do cpf com o primeiro resto + o segundo
        // resto.
        return nDigVerific.equals(nDigResult);
    }

    private boolean isCnpj(String cnpj) {
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");

        try{
            Long.parseLong(cnpj);
        } catch(NumberFormatException e){
            return false;
        }

        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14))
            return (false);
        char dig13, dig14;
        int sm, i, r, num, peso; // "try" - protege o código para eventuais
        // erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número: // por
                // exemplo, transforma o caractere '0' no inteiro 0 // (48 eh a
                // posição de '0' na tabela ASCII)
                num = cnpj.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = cnpj.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);
            // Verifica se os dígitos calculados conferem com os dígitos
            // informados.
            return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

}