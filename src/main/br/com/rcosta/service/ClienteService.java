package main.br.com.rcosta.service;


import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.repository.ClienteRepository;
import main.br.com.rcosta.repository.ClienteServiceRepository;
import main.br.com.rcosta.service.generic.GenericsService;

/**
 * @author riverson
 *
 */
public class ClienteService extends GenericsService<Cliente, Long> implements ClienteServiceRepository {
	public ClienteService(ClienteRepository clienteDto) {
		super(clienteDto);
	}

	@Override
	public Cliente buscarPorCPF(Long cpf) {
		return this.dao.consultar(cpf);
	}

}
