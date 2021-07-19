package br.ufrn.goallist.controller;

import br.ufrn.goallist.service.impl.DefaultPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PessoaController.class)
class PessoaControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DefaultPessoaService pessoaService;
}