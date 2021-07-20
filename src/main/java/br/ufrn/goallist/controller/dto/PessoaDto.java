package br.ufrn.goallist.controller.dto;

import br.ufrn.goallist.model.Meta;
import br.ufrn.goallist.model.Pessoa;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {
    private String nome;
    private List<Meta> metas;

    public static Pessoa transform(PessoaDto pessoaDto) {
        return Pessoa.builder()
                .nome(pessoaDto.getNome())
                .metas(pessoaDto.getMetas())
                .build();
    }
}
