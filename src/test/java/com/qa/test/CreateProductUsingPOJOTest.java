package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pojo.ProductPayload;
import com.qa.pojo.ProductResponse;
import com.qa.utils.ResponseUtils;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class CreateProductUsingPOJOTest extends BaseTest {

    @Test
    public void createProduct() {

        ProductPayload payload = new ProductPayload();
        payload.setTitle("POJO Product");
        payload.setPrice(199.99);
        payload.setDescription("Created using POJO");
        payload.setImage("https://i.pravatar.cc");
        payload.setCategory("electronics");

        Response response =
            given()
                .spec(requestSpec)
                .body(payload)
            .when()
                .post("/products");

        response.then().statusCode(201);
        
        int productId = ResponseUtils.getProductId(response);


        ProductResponse productResponse =
                response.as(ProductResponse.class);

        Assert.assertEquals(
        	    ResponseUtils.getTitle(response),
        	    payload.getTitle()
        	);
        Assert.assertEquals(productResponse.getPrice(), payload.getPrice());
        Assert.assertEquals(productResponse.getCategory(), payload.getCategory());
        Assert.assertTrue(productResponse.getId() > 0);
    }
}
