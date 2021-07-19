package br.ufrn.goallist.controller.dto;

import br.ufrn.goallist.model.Etapa;
import lombok.Data;

@Data
public class EtapaDto {
    private String titulo;
    private String descricao;

    public static Etapa transform(EtapaDto etapaDto) {
        return Etapa.builder()
                .titulo(etapaDto.getTitulo())
                .descricao(etapaDto.getDescricao())
                .build();
    }
}
