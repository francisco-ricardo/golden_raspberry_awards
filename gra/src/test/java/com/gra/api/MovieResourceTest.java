package com.gra.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class MovieResourceTest {

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

    // @Test
    // public void testGetMovieById() {
    //     // Assumes a movie with ID 1 exists in your test dataset
    //     RestAssured.given()
    //         .when().get("/movies/1")
    //         .then()
    //         .statusCode(anyOf(is(200), is(404))); // 200 if exists, 404 if not
    // }
}