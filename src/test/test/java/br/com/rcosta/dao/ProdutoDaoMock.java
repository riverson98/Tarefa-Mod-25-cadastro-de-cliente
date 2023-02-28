package test.test.java.br.com.rcosta.dao;


import main.br.com.rcosta.entity.Produto;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.ProdutoRepository;

import java.util.Collection;

/**
 * @author riverson
 *
 */
public class ProdutoDaoMock implements ProdutoRepository {

	@Override
	public Boolean cadastrar(Produto entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void excluir(String valor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Produto entity) throws TipoChaveNaoEncontradaException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto consultar(String valor) {
		Produto produto = new Produto();
		produto.setCodigo(valor);
		return produto;
	}

	@Override
	public Collection<Produto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
