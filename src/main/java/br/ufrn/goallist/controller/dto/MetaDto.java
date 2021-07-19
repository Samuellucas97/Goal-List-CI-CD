package br.ufrn.goallist.controller.dto;

import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.model.Meta;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MetaDto {
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoConclusao;

    public static Meta transform(MetaDto metaDto) {
        return Meta.builder()
                .titulo(metaDto.getTitulo())
                .descricao(metaDto.getDescricao())
                .dataInicio(metaDto.getDataInicio())
                .dataPrevisaoConclusao(metaDto.getDataPrevisaoConclusao())
                .build();
    }
}
