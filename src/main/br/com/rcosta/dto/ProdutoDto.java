package main.br.com.rcosta.dto;

import main.br.com.rcosta.dto.generic.GenericDto;
import main.br.com.rcosta.entity.Produto;
import main.br.com.rcosta.repository.ProdutoRepository;

public class ProdutoDto extends GenericDto<Produto, String> implements ProdutoRepository {

    public ProdutoDto() {
        super();
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entity, Produto entityCadastrado) {
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setDescricao(entity.getDescricao());
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setValor(entity.getValor());
    }
}
