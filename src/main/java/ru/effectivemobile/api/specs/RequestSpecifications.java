package ru.effectivemobile.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {

    private RequestSpecifications() {
    }

    public static RequestSpecification defaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080/")
                .addHeader("Authorization",
                        "Bearer " + "rp-test_PxS6WrN7ToS8B5qM6cgc3n8WYt8AUmJnH2Ca022mkhhT3bzmziypGNh_r_4FYfcx")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
