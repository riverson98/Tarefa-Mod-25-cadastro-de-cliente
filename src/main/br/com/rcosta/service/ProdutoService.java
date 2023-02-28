package main.br.com.rcosta.service;

import main.br.com.rcosta.entity.Produto;
import main.br.com.rcosta.repository.ProdutoRepository;
import main.br.com.rcosta.repository.ProdutoServiceRepository;
import main.br.com.rcosta.service.generic.GenericsService;

/**
 * @author riverson
 *
 */
public class ProdutoService extends GenericsService<Produto, String> implements ProdutoServiceRepository {

	public ProdutoService(ProdutoRepository dao) {
		super(dao);
	}

}
