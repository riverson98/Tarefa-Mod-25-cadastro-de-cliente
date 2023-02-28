package main.br.com.rcosta.repository;

import main.br.com.rcosta.entity.Cliente;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;

public interface ClienteServiceRepository {
    Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;

    Cliente buscarPorCPF(Long cpf);

    void excluir(Long cpf);

    void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;
}
