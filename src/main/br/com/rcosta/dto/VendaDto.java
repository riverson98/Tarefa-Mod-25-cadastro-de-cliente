package main.br.com.rcosta.dto;

import main.br.com.rcosta.dto.generic.GenericDto;
import main.br.com.rcosta.entity.Venda;
import main.br.com.rcosta.exception.TipoChaveNaoEncontradaException;
import main.br.com.rcosta.repository.VendaRepository;

public class VendaDto extends GenericDto<Venda, String> implements VendaRepository {
    @Override
    public Class<Venda> getTipoClasse() {
        return Venda.class;
    }

    @Override
    public void atualizarDados(Venda entity, Venda entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setStatus(entity.getStatus());
    }

    @Override
    public void excluir(String valor) {
        throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
    }

    @Override
    public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException {
        venda.setStatus(Venda.Status.CONCLUIDA);
        super.alterar(venda);
    }
}
