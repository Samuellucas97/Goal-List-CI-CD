package br.ufrn.goallist.controller.dto;

import br.ufrn.goallist.model.Objetivo;
import lombok.Data;

@Data
public class ObjetivoDto {
    private String titulo;
    private String descricao;

    public static Objetivo transform(ObjetivoDto objetivoDto) {
        return Objetivo.builder()
                .titulo(objetivoDto.getTitulo())
                .descricao(objetivoDto.getDescricao())
                .build();
    }
}
