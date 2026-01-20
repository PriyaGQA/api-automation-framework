package com.qa.utils;

import io.restassured.response.Response;

public final class ResponseUtils {

	private ResponseUtils() {}

    // For GET /products
    public static int getFirstProductId(Response response) {
        return response.jsonPath().getInt("[0].id");
    }

    // For POST /products
    public static int getProductId(Response response) {
        return response.jsonPath().getInt("id");
    }

    public static String getTitle(Response response) {
        return response.jsonPath().getString("title");
    }

    public static double getPrice(Response response) {
        return response.jsonPath().getDouble("price");
    }
}

