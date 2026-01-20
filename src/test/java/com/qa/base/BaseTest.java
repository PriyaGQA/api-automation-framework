package com.qa.base;

import org.testng.annotations.BeforeClass;
import com.qa.filters.FailureLoggingFilter;

import com.qa.constants.FrameworkConstants;
import com.qa.utils.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
	
	//branch practice change

    protected RequestSpecification requestSpec;
    protected ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {

        String baseUri = System.getProperty(
                "baseUri",
                ConfigReader.getValue(FrameworkConstants.BASE_URI)
        );

        System.out.println("Base URI Loaded = " + baseUri);

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setConfig(RestAssured.config()
                    .logConfig(LogConfig.logConfig()
                    .enableLoggingOfRequestAndResponseIfValidationFails()))
                .addHeader(
                    FrameworkConstants.CONTENT_TYPE,
                    FrameworkConstants.APPLICATION_JSON
                )
                .addHeader(
                    "Authorization",
                    "Bearer " + ConfigReader.getAuthToken()
                )
                .addFilter(new FailureLoggingFilter())
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

}
