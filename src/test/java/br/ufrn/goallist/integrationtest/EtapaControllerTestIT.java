package br.ufrn.goallist.integrationtest;

import br.ufrn.goallist.enums.EtapaEstado;
import br.ufrn.goallist.enums.MetaEstado;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.model.Meta;
import br.ufrn.goallist.repository.EtapaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class EtapaControllerTestIT {
    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private EtapaRepository etapaRepositoryMock;

    private String baseUrl = "/v1/etapa/";

    @Test
    void getAll_ShouldReturnEtapaList_WhenSuccessful() {
        final Etapa etapa = creatorEtapa();

        when(etapaRepositoryMock.findAll())
                .thenReturn(List.of(etapa));

        final ResponseEntity<List<Etapa>> resultResponse = restTemplate.exchange( baseUrl + "", GET,
                null, new ParameterizedTypeReference<List<Etapa>>() {
        });

        final List<Etapa> resultEtapaList = resultResponse.getBody();

        assertAll(
                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(OK),
                () -> assertThat(resultEtapaList).isNotNull(),
                () -> assertThat(resultEtapaList).isNotEmpty(),
                () -> assertThat(resultEtapaList).contains(etapa)
        );
    }

    @Test
    void save_ShouldCreateEtapa_WhenSuccessful() {
        final Etapa etapaWithId = creatorEtapaWithId();
        final Etapa etapa = creatorEtapa();

        when(etapaRepositoryMock.save(etapa)).thenReturn(etapaWithId);

        ResponseEntity<Etapa> responseEntity = restTemplate.exchange(baseUrl + "", POST,
                new HttpEntity<>(etapa), new ParameterizedTypeReference<Etapa>() {
            });

        final Etapa resultEtapa = responseEntity.getBody();

        assertAll(
            () -> assertThat(responseEntity).isNotNull(),
            () -> assertThat(responseEntity.getStatusCode()).isEqualTo(ACCEPTED),
            () -> assertThat(resultEtapa.getId()).isNotNull()
            () -> assertThat(resultEtapa.getId()).isEqualTo(etapaWithId.getId())
        );
    }

    @Test
    void delete_ShouldRemoveEtapa_WhenSucessful() {
        doNothing().when(etapaRepositoryMock)
                .delete(any(Etapa.class));

        ResponseEntity<Void> responseEntity = restTemplate.exchange(baseUrl + "{id}", DELETE,
                null, Void.class, 1);

        assertAll(
                () -> assertThat(responseEntity).isNotNull(),
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(NO_CONTENT),
                () -> assertThat(responseEntity.getBody()).isNull()
        );
    }

    @Test
    void delete_ShouldReturnNotFound_WhenEtapaDoesntExist() {
        ResponseEntity<Void> responseEntity = restTemplate.exchange(baseUrl + "{id}", DELETE,
                null, Void.class, 1);

        assertThat(responseEntity.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    private Etapa creatorEtapa() {
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

    private Etapa creatorEtapaWithId() {
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