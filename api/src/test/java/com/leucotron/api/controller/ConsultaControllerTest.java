package com.leucotron.api.controller;

import com.leucotron.api.domain.consulta.AgendamentoConsultaDTO;
import com.leucotron.api.domain.consulta.AgendamentoConsultaService;
import com.leucotron.api.domain.consulta.ConsultaDetalhamentoDTO;
import com.leucotron.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AgendamentoConsultaDTO> agendamentoConsultaDTOJson;

    @Autowired
    private JacksonTester<ConsultaDetalhamentoDTO> consultaDetalhamentoDTOJson;

    @MockBean
    private AgendamentoConsultaService agendamentoConsultaService;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consulta"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var consultaDetalhamentoDTO = new ConsultaDetalhamentoDTO(null, 2l, 5l, data);
        when(agendamentoConsultaService.agendar(any())).thenReturn(consultaDetalhamentoDTO);

        var response = mvc
                .perform(
                        post("/consulta")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(agendamentoConsultaDTOJson.write(
                                        new AgendamentoConsultaDTO(2l, especialidade, 5l, data)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = consultaDetalhamentoDTOJson.write(
                consultaDetalhamentoDTO
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}