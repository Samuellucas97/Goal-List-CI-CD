package br.ufrn.goallist;

import br.ufrn.goallist.exception.EtapaNotFoundException;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.repository.EtapaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static br.ufrn.goallist.utils.EtapaUtils.creatorEtapaDto;
import static br.ufrn.goallist.utils.EtapaUtils.creatorEtapaWithId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
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
    void getAll_ShouldReturnEmptyList_WhenThereIsNotEtapa() {

        when(etapaRepositoryMock.findAll())
                .thenReturn(List.of());

        final ResponseEntity<List<Etapa>> resultResponse = restTemplate
                .exchange( baseUrl + "", GET, null,
                        new ParameterizedTypeReference<List<Etapa>>() {
                });

        final List<Etapa> resultEtapaList = resultResponse.getBody();

        assertAll(
                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(OK),
                () -> assertThat(resultEtapaList).isNotNull(),
                () -> assertThat(resultEtapaList).isEmpty()
        );
    }


    @Test
    void getAll_ShouldReturnEtapaList_WhenSuccessful() {

        when(etapaRepositoryMock.findAll())
                .thenReturn(List.of(creatorEtapaWithId()));

        final ResponseEntity<List<Etapa>> resultResponse = restTemplate
                .exchange( baseUrl + "", GET, null,
                        new ParameterizedTypeReference<List<Etapa>>() {
                });

        final List<Etapa> resultEtapaList = resultResponse.getBody();

        assertAll(
                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(OK),
                () -> assertThat(resultEtapaList).isNotNull(),
                () -> assertThat(resultEtapaList).isNotEmpty(),
                () -> assertThat(resultEtapaList).contains(creatorEtapaWithId())
        );
    }

    @Test
    void create_ShouldCreateEtapa_WhenSuccessful() {

        when(etapaRepositoryMock.save(any(Etapa.class))).thenReturn(creatorEtapaWithId());

        ResponseEntity<Etapa> responseEntity = restTemplate.exchange(baseUrl + "", POST,
                new HttpEntity<>(creatorEtapaDto()), new ParameterizedTypeReference<Etapa>() {
            });

        final Etapa resultEtapa = responseEntity.getBody();

        assertAll(
            () -> assertThat(responseEntity).isNotNull(),
            () -> assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED),
            () -> assertThat(resultEtapa).isNotNull(),
            () -> assertThat(resultEtapa.getId()).isNotNull(),
            () -> assertThat(resultEtapa.getId()).isEqualTo(creatorEtapaWithId().getId())
        );
    }

    @Test
    void deleteById_ShouldRemoveEtapa_WhenSucessful() {
        doNothing().when(etapaRepositoryMock).delete(any(Etapa.class));

        when(etapaRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(creatorEtapaWithId()));

        Long idEtapa = 1L;
        ResponseEntity<Void> responseEntity = restTemplate.exchange(baseUrl + "{id}", DELETE,
                null, Void.class, idEtapa);

        assertAll(
                () -> assertThat(responseEntity).isNotNull(),
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(NO_CONTENT),
                () -> assertThat(responseEntity.getBody()).isNull()
        );
    }


    @Test
    void deleteById_ShouldReturnNotFound_WhenEtapaDoesntExist() {

        Long idEtapa = 1L;
        ResponseEntity<Void> responseEntity = restTemplate.exchange(baseUrl + "{id}", DELETE,
                null, Void.class, idEtapa);

        assertThat(responseEntity.getStatusCode()).isEqualTo(NOT_FOUND);
    }

    @Test
    void update_ShouldUpdateEtapa_WhenSuccesful() {

        when(etapaRepositoryMock.findById(anyLong()))
                .thenReturn(Optional.of(creatorEtapaWithId()));

        when(etapaRepositoryMock.save(any(Etapa.class))).thenReturn(creatorEtapaWithId());

        Long idEtapa = 1L;
        ResponseEntity<Etapa> responseEntity = restTemplate.exchange(baseUrl + "{id}", PUT,
                new HttpEntity<>(creatorEtapaDto()), Etapa.class, idEtapa);

        final Etapa resultEtapa = responseEntity.getBody();
        assertAll(
                () -> assertThat(responseEntity).isNotNull(),
                () -> assertThat(responseEntity.getStatusCode()).isEqualTo(OK),
                () -> assertThat(resultEtapa).isNotNull(),
                () -> assertThat(resultEtapa.getId()).isNotNull(),
                () -> assertThat(resultEtapa.getId()).isEqualTo(creatorEtapaWithId().getId())
        );
    }
}