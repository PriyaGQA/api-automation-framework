package com.qa.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class FailureLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        if (response.getStatusCode() >= 400) {
            System.out.println("----- REQUEST -----");
            requestSpec.log().all();

            System.out.println("----- RESPONSE -----");
            response.then().log().all();
        }

        return response;
    }
}

