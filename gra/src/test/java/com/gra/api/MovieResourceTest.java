package com.gra.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

/**
 * Classe de teste para o recurso MovieResource.
 * Testa os endpoints relacionados aos filmes.
 * Verifica se o endpoint /movies retorna a lista de filmes corretamente.
 */
@QuarkusTest
public class MovieResourceTest {
    

    /**
     * Testa o endpoint /movies para garantir que retorna a lista de filmes.
     * Verifica se o status da resposta e 200 e se a lista nao esta vazia.
     * Verifica tambem se os campos id, title, year, producers e winner estao presentes.
     */
    @Test
    public void testGetAllMovies() {
        RestAssured.given()
            .when().get("/movies")
            .then()
            .statusCode(200)
            .body("$", not(empty()))
            .body("[0].id", notNullValue())
            .body("[0].title", notNullValue())
            .body("[0].year", notNullValue())
            .body("[0].producers", notNullValue())
            .body("[0].winner", notNullValue());
    }

}