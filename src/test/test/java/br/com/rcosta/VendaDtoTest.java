package test.test.java.br.com.rcosta;

import main.br.com.rcosta.dto.ClienteDto;
import main.br.com.rcosta.dto.ProdutoDto;
import main.br.com.rcosta.dto.VendaDto;
import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.entity.Produto;
import main.br.com.rcosta.entity.Venda;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ClienteRepository;
import main.br.com.rcosta.repository.ProdutoRepository;
import main.br.com.rcosta.repository.VendaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author riverson
 *
 */
public class VendaDtoTest {
	
	private VendaRepository vendaRepository;
	
	private ClienteRepository clienteRepository;
	
	private ProdutoRepository produtoRepository;
	
	private Cliente cliente;
	
	private Produto produto;
	
	public VendaDtoTest() {
		vendaRepository = new VendaDto();
		clienteRepository = new ClienteDto();
		produtoRepository = new ProdutoDto();
	}
	
	@BeforeEach
	public void init() throws TipoChaveNaoEncontradaException {
		this.cliente = cadastrarCliente();
		this.produto = cadastrarProduto("A1", BigDecimal.TEN);
	}

	
	@Test
	public void pesquisar() throws TipoChaveNaoEncontradaException {
		Venda venda = criarVenda("A1");
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		Venda vendaConsultada = vendaRepository.consultar(venda.getCodigo());
		assertNotNull(vendaConsultada);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		Venda venda = criarVenda("A2");
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	
	@Test
	public void cancelarVenda() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A3";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		venda.setStatus(Venda.Status.CANCELADA);
		vendaRepository.alterar(venda);
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		assertEquals(codigoVenda, vendaConsultada.getCodigo());
		assertEquals(Venda.Status.CANCELADA, vendaConsultada.getStatus());
	}
	
	@Test
	public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A4";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(produto, 1);
		
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(30)));
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	@Test
	public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A5";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	@Test
	public void salvarProdutoExistente() throws TipoChaveNaoEncontradaException {
		Venda venda = criarVenda("A6");
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
	
		Boolean retorno1 = vendaRepository.cadastrar(venda);
		assertFalse(retorno1);
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	@Test
	public void removerProduto() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A7";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		
		vendaConsultada.removerProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 2);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	@Test
	public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A8";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		
		vendaConsultada.removerProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 2);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	@Test
	public void removerTodosProdutos() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A9";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());
		
		Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
		assertNotNull(prod);
		assertEquals(codigoVenda, prod.getCodigo());
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		vendaConsultada.adicionarProduto(prod, 1);
		assertTrue(venda.getQuantidadeTotalProdutos() == 3);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
		
		
		vendaConsultada.removerTodosProdutos();
		assertTrue(venda.getQuantidadeTotalProdutos() == 0);
		assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(0)));
		assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
	} 
	
	@Test
	public void finalizarVenda() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A10";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		vendaRepository.finalizarVenda(venda);
		
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(venda.getStatus(), vendaConsultada.getStatus());
	}
	
	@Test()
	public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException {
		String codigoVenda = "A11";
		Venda venda = criarVenda(codigoVenda);
		Boolean retorno = vendaRepository.cadastrar(venda);
		assertTrue(retorno);
		assertNotNull(venda);
		assertEquals(codigoVenda, venda.getCodigo());

		vendaRepository.finalizarVenda(venda);
		Venda vendaConsultada = vendaRepository.consultar(codigoVenda);
		assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
		assertEquals(venda.getStatus(), vendaConsultada.getStatus());

		Assertions.assertThrows(UnsupportedOperationException.class, () ->
				vendaConsultada.adicionarProduto(this.produto, 1));
		
	}

	private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException {
		Produto produto = new Produto();
		produto.setCodigo(codigo);
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(valor);
		produtoRepository.cadastrar(produto);
		return produto;
	}

	private Cliente cadastrarCliente() throws TipoChaveNaoEncontradaException {
		Cliente cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEnd("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTel(1199999999L);
		clienteRepository.cadastrar(cliente);
		return cliente;
	}
	
	private Venda criarVenda(String codigo) {
		Venda venda = new Venda();
		venda.setCodigo(codigo);
		venda.setDataVenda(Instant.now());
		venda.setCliente(this.cliente);
		venda.setStatus(Venda.Status.INICIADA);
		venda.adicionarProduto(this.produto, 2);
		return venda;
	}

}
