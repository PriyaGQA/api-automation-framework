package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pojo.ProductPayload;
import com.qa.pojo.ProductResponse;

import static io.restassured.RestAssured.*;

public class UpdateProductUsingPOJOTest extends BaseTest {
	
	@Test
	public void updateProductUsingPOJOTest() {
		
		ProductPayload createPayload = new ProductPayload();
		createPayload.setTitle("Initial Product");
		createPayload.setPrice(100.0);
		createPayload.setDescription("Created product");
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
			
			
			
			ProductPayload updatePayload = new ProductPayload();
			updatePayload.setTitle("Updated POJO Product");
			updatePayload.setPrice(299.99);
			updatePayload.setDescription("Updated using PUT");
			updatePayload.setImage("https://i.pravatar.cc");
			updatePayload.setCategory("electronics");

			
			ProductResponse updateResponse =
				    given()
				        .spec(requestSpec)
				        .pathParam("id", productId)
				        .body(updatePayload)
				    .when()
				        .put("/products/{id}")
				    .then()
				        .statusCode(200)
				        .extract()
				        .as(ProductResponse.class);

			
			Assert.assertEquals(updateResponse.getTitle(), updatePayload.getTitle());
			Assert.assertEquals(updateResponse.getPrice(), updatePayload.getPrice());
			Assert.assertEquals(updateResponse.getCategory(), updatePayload.getCategory());
			Assert.assertEquals(updateResponse.getId(), productId);



	}
	

}
