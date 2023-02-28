package main.br.com.rcosta.dto;

import main.br.com.rcosta.dto.generic.GenericDto;
import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.repository.ClienteRepository;

/**
 * @author riverson
 */
public class ClienteDto extends GenericDto<Cliente, Long> implements ClienteRepository {

    public ClienteDto() {
        super();
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente entity, Cliente entityCadastrado) {
        Cliente.builder()
                .cidade(entity.getCidade())
                .cpf(entity.getCpf())
                .end(entity.getEnd())
                .estado(entity.getEstado())
                .nome(entity.getNome())
                .numero(entity.getNumero())
                .tel(entity.getTel())
                .build();
    }
}
