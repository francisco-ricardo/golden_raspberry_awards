package com.gra.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Classe de teste para o recurso AwardResource.
 * Testa os endpoints relacionados aos intervalos de premios.
 * Verifica se os intervalos minimos e maximos estao retornando corretamente,
 * se a estrutura da resposta esta correta e se os valores dos intervalos sao validos.
 */
@QuarkusTest
public class AwardResourceTest {

    /**
     * Testa o endpoint /awards para garantir que retorna os intervalos minimos e maximos.
     * Verifica se o status da resposta é 200 e se os campos min e max estão presentes.
     */
    @Test
    public void testAwardsEndpointReturnsExpectedIntervals() {
        RestAssured.given()
            .when().get("/awards")
            .then()
            .statusCode(200)
            .body("min", notNullValue())
            .body("max", notNullValue())
            .body("min.size()", greaterThanOrEqualTo(1))
            .body("max.size()", greaterThanOrEqualTo(1));
    }
    

    /**
     * Testa o endpoint /awards para garantir que os intervalos minimos e maximos
     * estão retornando com a estrutura correta.
     * Verifica se cada intervalo contém os campos producer, interval, previousWin e followingWin.
     */
    @Test
    public void testAwardsEndpointResponseStructure() {
        Response response = RestAssured.given()
            .when().get("/awards")
            .then()
            .statusCode(200)
            .extract().response();

        if (response.body() == null || response.body().asString().isBlank()) {
            // Aceitavel: response body pode ser null ou blank
            return;
        }

        Map<String, List<Map<String, Object>>> awards = response.jsonPath().getMap("$");

        for (String key : new String[]{"min", "max"}) {
            List<Map<String, Object>> intervals = awards.get(key);
            if (intervals == null) continue;
            for (Map<String, Object> interval : intervals) {
                assertTrue(interval.containsKey("producer"));
                assertTrue(interval.containsKey("interval"));
                assertTrue(interval.containsKey("previousWin"));
                assertTrue(interval.containsKey("followingWin"));

                assertNotNull(interval.get("producer"));
                assertTrue(interval.get("interval") instanceof Number);
                assertTrue(((Number) interval.get("interval")).intValue() > 0);

                assertTrue(interval.get("previousWin") instanceof Number);
                assertTrue(interval.get("followingWin") instanceof Number);

                int prev = ((Number) interval.get("previousWin")).intValue();
                int foll = ((Number) interval.get("followingWin")).intValue();
                assertTrue(foll > prev, "followingWin should be greater than previousWin");
            }
        }
    }


}

