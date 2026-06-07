package ru.effectivemobile.api.services;

import io.restassured.response.Response;
import ru.effectivemobile.api.client.ApiClient;
import ru.effectivemobile.api.models.CreateLaunchRequest;
import ru.effectivemobile.api.models.FinishLaunchRequest;
import ru.effectivemobile.api.models.UpdateLaunchRequest;
import ru.effectivemobile.config.ApiConfig;
import ru.effectivemobile.config.AppConfig;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class LaunchesService extends ApiClient {

    private final ApiConfig api = AppConfig.api();

    public Response getLaunches() {
        return given()
                .spec(requestSpec)
                .when()
                .get(api.launchPath());
    }

    public Response createLaunch(CreateLaunchRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post(api.launchPath());
    }

    public Response getLaunchById(UUID id) {
        return given()
                .spec(requestSpec)
                .when()
                .get("%s/%s".formatted(api.launchPath(), id));
    }

    public Response updateLaunch(Long id, UpdateLaunchRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .put("%s/%s/update".formatted(api.launchPath(), id));
    }

    public Response finishLaunch(UUID id, FinishLaunchRequest request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .put("%s/%s/finish".formatted(api.launchPath(), id));
    }

    public Response deleteLaunch(Long id) {
        return given()
                .spec(requestSpec)
                .when()
                .delete("%s/%s".formatted(api.launchPath(), id));
    }
}
