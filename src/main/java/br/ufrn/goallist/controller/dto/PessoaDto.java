package br.ufrn.goallist.controller.dto;

import br.ufrn.goallist.model.Objetivo;
import br.ufrn.goallist.model.Pessoa;
import lombok.Data;

import java.util.List;

@Data
public class PessoaDto {
    private String nome;
    private List<Objetivo> objetivos;

    public static Pessoa transform(PessoaDto pessoaDto) {
        return Pessoa.builder()
                .nome(pessoaDto.getNome())
                .objetivos(pessoaDto.getObjetivos())
                .build();
    }
}
