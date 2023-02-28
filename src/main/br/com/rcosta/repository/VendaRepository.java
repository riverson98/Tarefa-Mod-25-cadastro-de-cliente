package main.br.com.rcosta.repository;

import main.br.com.rcosta.entity.Venda;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;

public interface VendaRepository extends GenericRepository<Venda, String> {
    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}
