package com.leucotron.api.controller;

import com.leucotron.api.domain.endereco.Endereco;
import com.leucotron.api.domain.endereco.EnderecoDTO;
import com.leucotron.api.domain.medico.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<MedicoCadastroDTO> medicoCadastroDTOJson;

    @Autowired
    private JacksonTester<MedicoDetalhamentoDTO> medicoDetalhamentoDTOJson;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        var response = mvc
                .perform(post("/medico"))
                .andReturn().getResponse();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {
        var medicoCadastroDTO = new MedicoCadastroDTO(
                "Medico",
                "medico@clinica",
                "35999999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                getEnderecoDTO());

        when(medicoRepository.save(any())).thenReturn(new Medico(medicoCadastroDTO));

        var response = mvc
                .perform(post("/medico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicoCadastroDTOJson.write(medicoCadastroDTO).getJson()))
                .andReturn().getResponse();

        var medicoDetalhamentoDTO = new MedicoDetalhamentoDTO(
                null,
                medicoCadastroDTO.nome(),
                medicoCadastroDTO.email(),
                medicoCadastroDTO.telefone(),
                medicoCadastroDTO.crm(),
                medicoCadastroDTO.especialidade(),
                new Endereco(medicoCadastroDTO.endereco())
        );
        var jsonEsperado = medicoDetalhamentoDTOJson.write(medicoDetalhamentoDTO).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private EnderecoDTO getEnderecoDTO() {
        return new EnderecoDTO(
                "Rua Principal",
                "0",
                "00000000",
                "Centro",
                "Pouso Alegre",
                "MG",
                "37550000"
        );
    }

}