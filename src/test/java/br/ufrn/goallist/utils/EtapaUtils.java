package br.ufrn.goallist.utils;

import br.ufrn.goallist.controller.dto.EtapaDto;
import br.ufrn.goallist.enums.EtapaEstado;
import br.ufrn.goallist.enums.MetaEstado;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.model.Meta;

import java.time.LocalDate;

public class EtapaUtils {
    public static EtapaDto creatorEtapaDto() {
        return EtapaDto.builder()
                .titulo("fake title")
                .descricao("fake description")
                .estado("NÃO CONCLUÍDA")
                .metaTitulo("fake title")
                .build();
    }

    public static Etapa creatorEtapa() {
        return Etapa.builder()
                .titulo("fake title")
                .descricao("fake description")
                .meta(
                        Meta.builder()
                                .titulo("fake title")
                                .descricao("fake description")
                                .estado(MetaEstado.NAO_CONCLUIDA)
                                .dataConclusao(LocalDate.now())
                                .build()
                )
                .estado(EtapaEstado.NAO_CONCLUIDA)
                .build();
    }

    public static Etapa creatorEtapaWithId() {
        Etapa etapa = Etapa.builder()
                .titulo("fake title")
                .descricao("fake description")
                .meta(
                        Meta.builder()
                                .titulo("fake title")
                                .descricao("fake description")
                                .estado(MetaEstado.NAO_CONCLUIDA)
                                .dataConclusao(LocalDate.now())
                                .build()
                )
                .estado(EtapaEstado.NAO_CONCLUIDA)
                .build();

        etapa.setId(1L);
        return etapa;
    }

}
