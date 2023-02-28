package main.br.com.rcosta.service.generic;


import main.br.com.rcosta.dto.Persistente;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.GenericRepository;
import main.br.com.rcosta.repository.GenericsServiceRepository;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author riverson
 *
 */
public abstract class GenericsService<T extends Persistente, E extends Serializable>
	implements GenericsServiceRepository<T, E> {
	
	protected GenericRepository<T,E> dao;
	
	public GenericsService(GenericRepository<T,E> dao) {
		this.dao = dao;
	}

	@Override
	public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
		return this.dao.cadastrar(entity);
	}

	@Override
	public void excluir(E valor) {
		this.dao.excluir(valor);
	}

	@Override
	public void alterar(T entity) throws TipoChaveNaoEncontradaException {
		this.dao.alterar(entity);
	}

	@Override
	public T consultar(E valor) {
		return this.dao.consultar(valor);
	}

	@Override
	public Collection<T> buscarTodos() {
		return this.dao.buscarTodos();
	}

}
