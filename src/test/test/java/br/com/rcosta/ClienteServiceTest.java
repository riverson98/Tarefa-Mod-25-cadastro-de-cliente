package test.test.java.br.com.rcosta;


import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ClienteRepository;
import main.br.com.rcosta.repository.ClienteServiceRepository;
import main.br.com.rcosta.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.test.java.br.com.rcosta.dao.ClienteDaoMock;

/**
 * @author riverson
 *
 */
public class ClienteServiceTest {
	
	private ClienteServiceRepository clienteService;
	
	private Cliente cliente;
	
	public ClienteServiceTest() {
		ClienteRepository dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}
	
	@BeforeEach
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		
	}
	
	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assertions.assertNotNull(clienteConsultado);
	}
	
	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		Boolean retorno = clienteService.cadastrar(cliente);

		Assertions.assertTrue(retorno);
	}
	
	@Test
	public void excluirCliente() {
		clienteService.excluir(cliente.getCpf());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Rodrigo Pires");
		clienteService.alterar(cliente);

		Assertions.assertEquals("Rodrigo Pires", cliente.getNome());
	}
}
