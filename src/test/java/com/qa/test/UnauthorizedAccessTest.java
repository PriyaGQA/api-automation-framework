package com.qa.test;

import org.testng.annotations.Test;
import com.qa.base.BaseTest;

import static io.restassured.RestAssured.*;

public class UnauthorizedAccessTest extends BaseTest {

    @Test
    public void getProductsWithoutAuth_ShouldReturn401() {

        given()
            .spec(requestSpec)
            .header("Authorization", "")   // override auth
        .when()
            .get("/products")
        .then()
            .statusCode(400);
    }
}

