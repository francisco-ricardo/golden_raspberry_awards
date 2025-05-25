package com.gra.api;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class AwardResourceTest {

    @Test
    public void testAwardsEndpointReturnsExpectedIntervals() {
        RestAssured.given()
            .when().get("/awards")
            .then()
            .statusCode(200)
            .body("min", notNullValue())
            .body("max", notNullValue())
            .body("min.size()", greaterThanOrEqualTo(1))
            .body("max.size()", greaterThanOrEqualTo(1))

            // Check for results are consistent with expected values
            .body("min.find { it.producer == 'Producer 1' }.interval", equalTo(1))
            .body("min.find { it.producer == 'Producer 1' }.previousWin", equalTo(2008))
            .body("min.find { it.producer == 'Producer 1' }.followingWin", equalTo(2009))
            .body("max.find { it.producer == 'Producer 1' }.interval", equalTo(99))
            .body("max.find { it.producer == 'Producer 1' }.previousWin", equalTo(1900))
            .body("max.find { it.producer == 'Producer 1' }.followingWin", equalTo(1999));
    }
}