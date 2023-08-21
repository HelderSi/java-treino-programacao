package br.com.coruja.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.coruja.application.model.entities.Aluno;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {
    @Autowired
    protected WebTestClient web;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
    }

    @Test
    public void deve_salvar_um_novo_aluno() {
        Aluno aluno = new Aluno("helder", "email");
        web.post()
                .uri("/alunos")
                .accept(MediaType.ALL)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Aluno.class)
                .value(c -> assertEquals(aluno, c));
    }

}