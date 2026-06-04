package ru.effectivemobile.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecifications {

    private ResponseSpecifications() {
    }

    public static ResponseSpecification ok() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
