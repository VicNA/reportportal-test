package ru.effectivemobile.api.services;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.effectivemobile.api.client.ApiClient;
import ru.effectivemobile.api.models.CreateLaunchRequest;
import ru.effectivemobile.api.models.FinishLaunchRequest;
import ru.effectivemobile.api.models.UpdateLaunchRequest;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class LaunchesService extends ApiClient {

    private static final Logger logger = LogManager.getLogger(LaunchesService.class);

    public Response getLaunches() {
        logger.info("Getting launches");

        return given()
                .spec(requestSpec)
                .when()
                .get(api.launchPath());
    }

    public Response createLaunch(CreateLaunchRequest request) {
        logger.info("Creating launch");

        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .post(api.launchPath());
    }

    public Response getLaunchById(UUID id) {
        logger.info("Get launch by id '{}'", id);

        return given()
                .spec(requestSpec)
                .when()
                .get("%s/%s".formatted(api.launchPath(), id));
    }

    public Response updateLaunch(Long id, UpdateLaunchRequest request) {
        logger.info("Update launch '{}'", id);

        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .put("%s/%s/update".formatted(api.launchPath(), id));
    }

    public Response finishLaunch(UUID id, FinishLaunchRequest request) {
        logger.info("Finish launch '{}'", id);

        return given()
                .spec(requestSpec)
                .body(request)
                .when()
                .put("%s/%s/finish".formatted(api.launchPath(), id));
    }

    public Response deleteLaunch(Long id) {
        logger.info("Delete launch '{}'", id);

        return given()
                .spec(requestSpec)
                .when()
                .delete("%s/%s".formatted(api.launchPath(), id));
    }
}
