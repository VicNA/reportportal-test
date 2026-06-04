package ru.effectivemobile.api.services;

import io.restassured.response.Response;
import ru.effectivemobile.api.client.ApiClient;

import static io.restassured.RestAssured.given;

public class LaunchesService extends ApiClient {

    public Response getLaunches() {
        return given()
                .spec(requestSpec)
                .when()
                .get("/api/v1/" + "default_personal" + "/launch");
    }
}
