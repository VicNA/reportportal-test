package ru.effectivemobile.api.services;

import io.restassured.response.Response;
import ru.effectivemobile.api.client.ApiClient;
import ru.effectivemobile.api.models.CreateLaunchRequest;
import ru.effectivemobile.api.models.UpdateLaunchRequest;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class LaunchesService extends ApiClient {

    public Response getLaunches() {
        return given()
                .spec(requestSpec)
                .when()
                .get("/api/v1/" + "default_personal" + "/launch");
    }

    public Response createLaunch(CreateLaunchRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post("/api/v1/" + "default_personal" + "/launch");
    }

    public Response getLaunchById(UUID id) {
        return given()
                .spec(requestSpec)
                .when()
                .get("/api/v1/" + "default_personal" + "/launch/" + id);
    }

    public Response updateLaunch(Long id, UpdateLaunchRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .put("/api/v1/" + "default_personal" + "/launch/" + id + "/update");
    }

    public Response deleteLaunch(UUID id) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("/api/v1/" + "default_personal" + "/launch/" + id);
    }
}
