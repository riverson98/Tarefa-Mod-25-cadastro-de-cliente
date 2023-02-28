package main.br.com.rcosta.entity;

import lombok.*;
import main.anotacoes.TipoChave;
import main.br.com.rcosta.dto.Persistente;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cliente implements Persistente {
    private String nome;

    @TipoChave("getCpf")
    private Long cpf;

    private Long tel;

    private String end;

    private Integer numero;

    private String cidade;

    private String estado;
}
