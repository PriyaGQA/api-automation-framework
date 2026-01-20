package com.qa.test;

import org.testng.annotations.Test;
import com.qa.base.BaseTest;
import com.qa.constants.ApiTestData;
import com.qa.dataprovider.ProductDataProvider;
import com.qa.utils.ResponseUtils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetSingleProductTest extends BaseTest {
	
	@Test
	public void getProductById_dynamic() {

		Response response =
		        given()
		            .spec(requestSpec)
		        .when()
		            .get("/products");

		    // STEP 2: Extract productId dynamically
		    int productId = ResponseUtils.getFirstProductId(response);
		    
		    
	    given()
	        .spec(requestSpec)
	        .pathParam("id", productId)
	    .when()
	        .get("/products/{id}")
	    .then()
	     .statusCode(200)
	     .body("id", equalTo(productId))
	     .body("title", notNullValue())
	     .body("price", greaterThan(0f));
	}


	@Test(
		    dataProvider = "productTestData",
		    dataProviderClass = ProductDataProvider.class
		)
		public void getProductById(int productId, int expectedStatusCode) {

		    Response response =
		        given()
		            .spec(requestSpec)
		            .pathParam("id", productId)
		        .when()
		            .get("/products/{id}");

		    response.then().statusCode(expectedStatusCode);

		    if (expectedStatusCode == 200) {
		        response.then()
		            .body("id", notNullValue())
		            .body("title", notNullValue())
		            .body("price", instanceOf(Number.class))
		            .body("category", notNullValue())
		            .body("image", notNullValue())
		            .body("rating.rate", instanceOf(Number.class))
		            .body("rating.count", instanceOf(Number.class));
		    }
		}
    
    
    @Test
    public void getLimitedProducts() {

        given()
            .spec(requestSpec)
            .queryParam("limit", ApiTestData.PRODUCT_LIMIT)

        .when()
            .get("/products")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("size()", equalTo(ApiTestData.PRODUCT_LIMIT));

    }

}
