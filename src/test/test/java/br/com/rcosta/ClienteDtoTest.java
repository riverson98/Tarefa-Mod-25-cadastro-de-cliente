package test.test.java.br.com.rcosta;

import main.br.com.rcosta.dto.ClienteDto;
import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author riverson
 *
 */
public class ClienteDtoTest {
	
	private ClienteRepository clienteRepository;

	private Cliente cliente;
	
	public ClienteDtoTest() {
		clienteRepository = new ClienteDto();
	}
	
	@BeforeEach
	public void init() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		clienteRepository.cadastrar(cliente);
	}
	
	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteRepository.consultar(cliente.getCpf());
		Assertions.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setCpf(56565656565L);
		Boolean retorno = clienteRepository.cadastrar(cliente);
		assertTrue(retorno);
	}
	
	
	@Test
	public void excluirCliente() {
		clienteRepository.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Rodrigo Pires");
		clienteRepository.alterar(cliente);
		Assertions.assertEquals("Rodrigo Pires", cliente.getNome());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Cliente> list = clienteRepository.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
