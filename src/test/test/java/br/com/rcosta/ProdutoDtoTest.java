package test.test.java.br.com.rcosta;


import main.br.com.rcosta.dto.ProdutoDto;
import main.br.com.rcosta.entity.Produto;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author riverson
 *
 */
public class ProdutoDtoTest {
	
	private ProdutoRepository produtoRepository;

	private Produto produto;
	
	public ProdutoDtoTest() {
		produtoRepository = new ProdutoDto();
	}
	
	@BeforeEach
	public void init() throws TipoChaveNaoEncontradaException {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
		produtoRepository.cadastrar(produto);
	}
	
	@Test
	public void pesquisar() {
		Produto produto = this.produtoRepository.consultar(this.produto.getCodigo());
		Assertions.assertNotNull(produto);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		produto.setCodigo("A2");
		Boolean retorno = produtoRepository.cadastrar(produto);
		assertTrue(retorno);
	}
	
	@Test
	public void excluir() {
		produtoRepository.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		produto.setNome("Riverson");
		produtoRepository.alterar(produto);

		Assertions.assertEquals("Riverson", produto.getNome());
	}
	
	@Test
	public void buscarTodos() {
		Collection<Produto> list = produtoRepository.buscarTodos();
		assertTrue(list != null);
	}
}
