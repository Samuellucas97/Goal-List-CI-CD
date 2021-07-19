package br.ufrn.goallist.integrationtest;

import br.ufrn.goallist.model.Pessoa;
import br.ufrn.goallist.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.ACCEPTED;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
class PessoaControllerTestIT {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @MockBean
//    private PessoaRepository pessoaRepositoryMock;
//
//    private String baseUrl = "http://localhost:8080/v1/";
//
//    @Test
//    void getAll_ShouldReturnPessoaList_WhenSuccessful() {
//
//        final ResponseEntity<List<Pessoa>> resultResponse = restTemplate.exchange( baseUrl + "pessoas/", GET,
//                null, new ParameterizedTypeReference<List<Pessoa>>() {
//        });
//
//        final List<Pessoa> resultPessoaList = resultResponse.getBody();
//
//        assertAll(
//                () -> assertThat(resultResponse.getStatusCode()).isEqualTo(ACCEPTED),
//                () -> assertThat(resultPessoaList).isNotNull(),
//                () -> assertThat(resultPessoaList).isNotEmpty()
////                () -> assertThat(resultPessoaList).contains()
//        );
//    }

}