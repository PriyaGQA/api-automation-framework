package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pojo.ProductPayload;
import com.qa.pojo.ProductResponse;

import static io.restassured.RestAssured.*;

public class PartialUpdateUsingPOJOTest extends BaseTest {
	
	@Test
	public void partialUpdateUsingPOJOTest() {
		
		ProductPayload createPayload = new ProductPayload();
		createPayload.setTitle("Original Product");
		createPayload.setPrice(199.99);
		createPayload.setDescription("Original Description");
		createPayload.setImage("https://i.pravatar.cc");
		createPayload.setCategory("electronics");

		ProductResponse createResponse =
		    given()
		        .spec(requestSpec)
		        .body(createPayload)
		    .when()
		        .post("/products")
		    .then()
		        .statusCode(201)
		        .extract()
		        .as(ProductResponse.class);

		int productId = createResponse.getId();   

		
		ProductPayload patchPayload = new ProductPayload();
		patchPayload.setPrice(349.99);
		patchPayload.setTitle("Patched POJO Product");
		
		ProductResponse patchResponse =
			    given()
			        .spec(requestSpec)
			        .pathParam("id", productId)
			        .body(patchPayload)
			    .when()
			        .patch("/products/{id}")
			    .then()
			        .statusCode(200)
			        .extract()
			        .as(ProductResponse.class);
		
		
		Assert.assertEquals(patchResponse.getTitle(), patchPayload.getTitle());
		Assert.assertEquals(patchResponse.getPrice(), patchPayload.getPrice());
		Assert.assertEquals(patchResponse.getId(), productId);



		
	}
	

}
