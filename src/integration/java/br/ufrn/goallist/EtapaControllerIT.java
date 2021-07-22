package br.ufrn.goallist;

import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.repository.EtapaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static br.ufrn.goallist.controller.dto.EtapaDto.transform;
import static br.ufrn.goallist.utils.EtapaUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EtapaControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl = "/v1/etapa/";

    @Autowired
    private EtapaRepository etapaRepository;

    @BeforeAll
    void setUp() {
        assertThat(etapaRepository.findAll()).isEmpty();
    }

    @AfterEach
    void cleanUpEach() {
        final List<Etapa> etapaList = etapaRepository.findAll();
        etapaList.forEach( etapa -> etapaRepository.delete(etapa));
    }

    @Test
    void getAll_ShouldReturnEmptyList_WhenThereIsNotEtapa() {

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

        etapaRepository.save(creatorEtapa());
        assertThat(etapaRepository.findAll()).isNotEmpty();


//        final ResponseEntity<List<Etapa>> resultResponse = restTemplate
//                .exchange( baseUrl + "", GET, null,
//                        new ParameterizedTypeReference<List<Etapa>>() {
//                });
//
//        final List<Etapa> resultEtapaList = resultResponse.getBody();
//
//        assertAll(
//                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(OK)
////                () -> assertThat(resultEtapaList).isNotNull(),
////                () -> assertThat(resultEtapaList).isNotEmpty(),
////                () -> assertThat(resultEtapaList).contains(creatorEtapaWithId())
//        );
    }

    @Test
    void create_ShouldCreateFirstEtapa_WhenSuccessful() {

        ResponseEntity<Etapa> responseEntity = restTemplate.exchange(baseUrl + "", POST,
                new HttpEntity<>(creatorEtapaDto()), new ParameterizedTypeReference<Etapa>() {
            });

        final Etapa resultEtapa = responseEntity.getBody();

        Long idFirstEtapa = 1L;
        assertAll(
            () -> assertThat(responseEntity).isNotNull(),
            () -> assertThat(responseEntity.getStatusCode()).isEqualTo(CREATED),
            () -> assertThat(resultEtapa).isNotNull(),
            () -> assertThat(resultEtapa.getId()).isNotNull(),
            () -> assertThat(resultEtapa.getId()).isEqualTo(idFirstEtapa)
        );
    }

    @Test
    @Disabled
    void deleteById_ShouldRemoveEtapa_WhenSucessful() {

        etapaRepository.save(creatorEtapa());
        assertThat(etapaRepository.findAll()).isNotEmpty();

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

}