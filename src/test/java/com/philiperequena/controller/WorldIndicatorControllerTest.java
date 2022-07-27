package com.philiperequena.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class WorldIndicatorControllerTest {

    @Test
    public void testCountryCodeEndpoint() {
        given()
                .when().get("/country/code")
                .then()
                .statusCode(200)
                .body(anything());
    }

    @Test
    public void testCountryIndicatorEndpoint() {
        given()
                .when().get("/country/indicator/BR")
                .then()
                .statusCode(200)
                .body(anything());
    }

}