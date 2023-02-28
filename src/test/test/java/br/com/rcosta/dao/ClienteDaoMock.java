package test.test.java.br.com.rcosta.dao;

import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ClienteRepository;

import java.util.Collection;

public class ClienteDaoMock implements ClienteRepository {

	@Override
	public Boolean cadastrar(Cliente entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void excluir(Long valor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(Cliente entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente consultar(Long valor) {
		Cliente cliente = new Cliente();
		cliente.setCpf(valor);
		return cliente;
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}


}
