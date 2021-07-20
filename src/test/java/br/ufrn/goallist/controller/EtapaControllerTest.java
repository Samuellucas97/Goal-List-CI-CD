package br.ufrn.goallist.controller;

import br.ufrn.goallist.exception.EtapaNotFoundException;
import br.ufrn.goallist.model.Etapa;
import br.ufrn.goallist.service.impl.DefaultEtapaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static br.ufrn.goallist.utils.EtapaUtils.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EtapaController.class)
class EtapaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DefaultEtapaService etapaService;

    /*
     * Jackson mapper for Object -> JSON conversion
     */
    @Autowired
    ObjectMapper mapper;

    private final String baseUrl = "/v1/etapa/";

    @Test
    void getAll_ShouldReturnEtapaList_WhenSuccessful() throws Exception {

        when(etapaService.getAll())
                .thenReturn(List.of(creatorEtapaWithId()));

        final ResultActions response = mockMvc.perform(get(baseUrl)
                .contentType(APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].id").value(1));

    }

    @Test
    void create_ShouldReturnEtapaCreated_WhenSuccessful() throws Exception {

        when(etapaService.create(any(Etapa.class))).thenReturn(creatorEtapaWithId());

        final MockHttpServletRequestBuilder builder = post(baseUrl)
                .contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(creatorEtapaDto()));

        final ResultActions response = mockMvc.perform(builder);

        response.andExpect(status().isCreated())
                .andExpect(content().string(this.mapper.writeValueAsString(creatorEtapaWithId())));
    }

    @Test
    void delete_ShouldRemoveEtapa_WhenSuccesful() throws Exception {

        doNothing().when(etapaService).deleteById(anyLong());

        Long idEtapa = 1L;
        final ResultActions response = mockMvc.perform(
                    MockMvcRequestBuilders.delete(baseUrl + idEtapa.toString())
                    .contentType(APPLICATION_JSON)
        );

        response.andExpect(status().isNoContent());

        verify(etapaService, times(1)).deleteById(idEtapa);
    }

    @Test
    void delete_ShouldThrowsEtapaNotFoundException_WhenEtapaDoesNotExist() throws Exception {

        doThrow(EtapaNotFoundException.class)
                .when(etapaService)
                .deleteById(anyLong());

        Long idEtapa = 1L;
        final ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.delete(baseUrl + idEtapa.toString())
                        .contentType(APPLICATION_JSON)
        );

        response.andExpect(status().isNotFound());
    }

    @Test
    void update_ShouldUpdateEtapa_WhenSuccesful() throws Exception {
        when(etapaService.create(any(Etapa.class))).thenReturn(creatorEtapaWithId());

        final MockHttpServletRequestBuilder builder = put(baseUrl)
                .contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.mapper.writeValueAsBytes(creatorEtapaDto()));

        final ResultActions response = mockMvc.perform(builder);

        response.andExpect(status().isOk())
                .andExpect(content().string(this.mapper.writeValueAsString(creatorEtapaWithId())));
    }
}