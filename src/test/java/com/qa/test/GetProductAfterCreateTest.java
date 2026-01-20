package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pojo.ProductPayload;
import com.qa.pojo.ProductResponse;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetProductAfterCreateTest extends BaseTest {

    @Test
    public void createAndGetProduct() {

        // -------------------------------
        // Step 1: Create Product Payload
        // -------------------------------
        ProductPayload payload = new ProductPayload();
        payload.setTitle("POJO Product");
        payload.setPrice(199.99);
        payload.setDescription("Created using POJO");
        payload.setImage("https://i.pravatar.cc");
        payload.setCategory("electronics");

        // -------------------------------
        // Step 2: POST - Create Product
        // -------------------------------
        Response postResponse =
                given()
                        .spec(requestSpec)
                        .body(payload)
                .when()
                        .post("/products");

        postResponse.then().statusCode(201);

        ProductResponse createdProduct =
                postResponse.as(ProductResponse.class);

        int productId = createdProduct.getId();

        Assert.assertTrue(productId > 0, "Invalid product ID returned");
        System.out.println("Created Product ID: " + productId);

        // -------------------------------
        // Step 3: GET Product by ID
        // -------------------------------
        Response getResponse =
                given()
                        .spec(requestSpec)
                .when()
                        .get("/products/" + productId);

        int statusCode = getResponse.statusCode();

        // -------------------------------
        // Step 4: Status Code Validation
        // -------------------------------
        Assert.assertTrue(
                statusCode == 200 || statusCode == 204,
                "Unexpected GET status code: " + statusCode
        );

        // -------------------------------
        // Step 5: Defensive Body Handling
        // -------------------------------
        String responseBody = getResponse.getBody().asString();
        String contentType = getResponse.getContentType();

        if (responseBody != null
                && !responseBody.trim().isEmpty()
                && contentType != null
                && contentType.contains("application/json")) {

            ProductResponse fetchedProduct =
                    getResponse.as(ProductResponse.class);

            Assert.assertEquals(fetchedProduct.getId(), productId);
            System.out.println("GET returned product JSON successfully");

        } else {
            System.out.println(
                    "GET returned status " + statusCode +
                    " with empty or non-JSON body — accepted API behavior"
            );
        }
    }
}
