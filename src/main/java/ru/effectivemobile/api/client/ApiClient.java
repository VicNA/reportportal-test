package ru.effectivemobile.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.specification.RequestSpecification;
import ru.effectivemobile.api.specs.RequestSpecifications;
import ru.effectivemobile.config.ApiConfig;
import ru.effectivemobile.config.AppConfig;

public abstract class ApiClient {

    protected final ApiConfig api = AppConfig.api();

    protected final RequestSpecification requestSpec = RequestSpecifications.defaultSpec(api);

    static {
        ObjectMapper jacksonMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        RestAssured.config = RestAssured.config()
                .objectMapperConfig(
                        ObjectMapperConfig.objectMapperConfig()
                                .jackson2ObjectMapperFactory(
                                        (cls, charset) -> jacksonMapper));
    }
}
