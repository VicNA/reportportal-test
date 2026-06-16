package ru.effectivemobile.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.effectivemobile.api.data.LaunchFactory;
import ru.effectivemobile.api.models.CreateLaunchRequest;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.models.LaunchResponse;
import ru.effectivemobile.api.models.UpdateLaunchRequest;
import ru.effectivemobile.api.services.LaunchesService;
import ru.effectivemobile.api.specs.ResponseSpecifications;
import ru.effectivemobile.utils.LaunchesHelper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LaunchesApiTest extends BaseApiTest {

    private static final LaunchesService launchesService = new LaunchesService();
    private static final LaunchesHelper launchesHelper = new LaunchesHelper(launchesService);

    @AfterAll
    static void teardown() {
        launchesHelper.clear();
    }

    @DisplayName("Get list launches")
    @Test
    void shouldGetLaunches() {
        Response response = launchesService.getLaunches();

        response.then().spec(ResponseSpecifications.ok());

        assertFalse(response.jsonPath()
                .getList("content")
                .isEmpty());
    }

    @DisplayName("Create launch")
    @Test
    void shouldCreateLaunch() {
        Response response = launchesService.createLaunch(LaunchFactory.create());

        CreateLaunchResponse created = response.as(CreateLaunchResponse.class);
        LaunchResponse launch = launchesHelper.getLaunchById(created.getId());

        response.then().spec(ResponseSpecifications.created());

        assertNotNull(created.getId());
        assertEquals(created.getId(), launch.getUuid());
    }

    @DisplayName("Issue bad request when create launch")
    @Test
    void shouldIssueBadRequestWhenCreateLaunch() {
        CreateLaunchRequest request = LaunchFactory.create();
        request.setStartTime(null);

        Response response = launchesService.createLaunch(request);

        response.then().statusCode(400);
    }

    @DisplayName("Get launch by ID")
    @Test
    void shouldGetLaunchById() {
        UUID uuid = launchesHelper.createLaunch().getId();
        Response response = launchesService.getLaunchById(uuid);
        LaunchResponse launch = launchesHelper.asLaunchResponse(response);

        response.then().spec(ResponseSpecifications.ok());

        assertEquals(uuid, launch.getUuid());
    }

    @DisplayName("Update launch by ID")
    @Test
    void shouldUpdateLaunch() {
        UUID id = launchesHelper.createLaunch().getId();
        LaunchResponse launch = launchesHelper.getLaunchById(id);

        String newDescription = "Updated description";

        Response response = launchesService.updateLaunch(
                launch.getId(), new UpdateLaunchRequest(newDescription));

        LaunchResponse updated = launchesHelper.getLaunchById(id);

        response.then().spec(ResponseSpecifications.ok());

        assertEquals(newDescription, updated.getDescription());
    }

    @DisplayName("Delete launch by ID")
    @Test
    void shouldDeleteLaunch() {
        UUID id = launchesHelper.createLaunch().getId();
        launchesService.finishLaunch(id, LaunchFactory.finish());

        LaunchResponse launch = launchesHelper.getLaunchById(id);

        launchesService.deleteLaunch(launch.getId())
                .then()
                .spec(ResponseSpecifications.ok());

        launchesService.getLaunchById(id)
                .then()
                .spec(ResponseSpecifications.notFound());
    }

    @DisplayName("Check return 404 for unknown launch")
    @Test
    void shouldReturn404ForUnknownLaunch() {
        UUID randomId = UUID.randomUUID();

        launchesService.getLaunchById(randomId)
                .then()
                .spec(ResponseSpecifications.notFound());
    }
}
