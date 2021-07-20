package br.ufrn.goallist.controller.dto;

import br.ufrn.goallist.enums.EtapaEstado;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.model.Meta;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import static br.ufrn.goallist.enums.EtapaEstado.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtapaDto {
    private String titulo;
    private String descricao;
    private String estado;
    private String metaTitulo;

    public static Etapa transform(EtapaDto etapaDto) {

        EtapaEstado etapaEstado = EM_PROGRESSO;
        if (etapaDto.estado.equals("NÃO CONCLUÍDA") )
            etapaEstado = NAO_CONCLUIDA;
        else if (etapaDto.estado.equals("CONCLUÍDA") )
            etapaEstado = CONCLUIDA;

        return Etapa.builder()
                .titulo(etapaDto.titulo)
                .descricao(etapaDto.descricao)
                .estado(etapaEstado)
                .meta(Meta.builder()
                        .titulo(etapaDto.metaTitulo)
                        .build())
                .build();
    }
}
