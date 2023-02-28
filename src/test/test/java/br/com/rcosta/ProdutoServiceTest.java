package test.test.java.br.com.rcosta;

import main.br.com.rcosta.entity.Produto;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ProdutoRepository;
import main.br.com.rcosta.repository.ProdutoServiceRepository;
import main.br.com.rcosta.service.ProdutoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.test.java.br.com.rcosta.dao.ProdutoDaoMock;

import java.math.BigDecimal;

/**
 * @author riverson
 *
 */
public class ProdutoServiceTest {

	private ProdutoServiceRepository produtoService;
	
	private Produto produto;
	
	public ProdutoServiceTest() {
		ProdutoRepository dao = new ProdutoDaoMock();
		produtoService = new ProdutoService(dao);
	}
	
	@BeforeEach
	public void init() {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
	}
	
	@Test
	public void pesquisar() {
		Produto produtor = this.produtoService.consultar(produto.getCodigo());
		Assertions.assertNotNull(produtor);
	}
	
	@Test
	public void salvar() throws TipoChaveNaoEncontradaException {
		Boolean retorno = produtoService.cadastrar(produto);
		Assertions.assertTrue(retorno);
	}
	
	@Test
	public void excluir() {
		produtoService.excluir(produto.getCodigo());
	}
	
	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		produto.setNome("Rodrigo Pires");
		produtoService.alterar(produto);

		Assertions.assertEquals("Rodrigo Pires", produto.getNome());
	}
}
