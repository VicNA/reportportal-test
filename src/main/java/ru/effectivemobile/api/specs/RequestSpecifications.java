package ru.effectivemobile.api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.effectivemobile.config.ApiConfig;
import ru.effectivemobile.config.AppConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestSpecifications {

    public static RequestSpecification defaultSpec() {
        ApiConfig api = AppConfig.api();

        return new RequestSpecBuilder()
                .setBaseUri(api.endpoint())
                .addHeader("Authorization", "Bearer " + api.apiKey())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
