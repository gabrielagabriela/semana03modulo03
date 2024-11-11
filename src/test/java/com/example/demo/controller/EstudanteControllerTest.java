package com.example.demo.controller;

import com.example.demo.database.entities.Estudante;
import com.example.demo.service.EstudanteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EstudanteControllerTest {

    @Autowired
    EstudanteController estudanteController;

    @MockBean
    private EstudanteService estudanteService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void listarEstudantes() throws Exception {

        when(estudanteService.listarEstudantes())
                .thenReturn(Collections.emptyList());

        mockMvc.perform(post("/estudantes")
                        .content("{\n" +
                                "\t\"nome\":\"nome\",\n" +
                                "\t\"matricula\":\"1234\"\n" +
                                "}")
                        .queryParam("id_turma","1")
                )
                .andExpect(status().isOk());

    }

    @Test
    void buscarEstudantePorId() {

    }

    @Test
    void cadastrarEstudante() throws Exception {

        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());

        when(estudanteService.cadastrarEstudante("Joaquino",
                "11.22.33"))
                .thenReturn(estudante);

        mockMvc.perform(
                        post("/estudantes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "\t\"nome\":\"Joaquino\",\n" +
                                        "\t\"matricula\": \"11.22.33\"\n" +
                                        "}")
                )
                .andExpect(status().isOk());
    }

    @Test
    void cadastrarEstudanteErro400() throws Exception {

        Estudante estudante = new Estudante(1L,
                "Joaquino",
                "11.22.33",
                Collections.emptyList());

        when(estudanteService.cadastrarEstudante("Joaquino",
                "11.22.33"))
                .thenReturn(estudante);

        mockMvc.perform(
                        post("/estudantes")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void atualizarEstudante() {
    }

    @Test
    void removerEstudante() {
    }
}