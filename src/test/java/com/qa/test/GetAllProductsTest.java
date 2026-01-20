package com.qa.test;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;

public class GetAllProductsTest extends BaseTest {

    @Test
    public void getAllProducts() {

        given()
            .spec(requestSpec)
        .when()
            .get("/products")
        .then()
            .spec(responseSpec)
            .statusCode(200);
    }
}