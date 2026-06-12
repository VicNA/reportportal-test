package ru.effectivemobile.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.effectivemobile.api.data.LaunchFactory;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.models.LaunchResponse;
import ru.effectivemobile.api.models.UpdateLaunchRequest;
import ru.effectivemobile.api.services.LaunchesService;
import ru.effectivemobile.api.specs.ResponseSpecifications;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LaunchesApiTest extends BaseApiTest {

    private static final LaunchesService launchesService = new LaunchesService();

    @DisplayName("Get list launches")
    @Test
    void shouldGetLaunches() {
        Response response = launchesService.getLaunches();

        response.then().spec(ResponseSpecifications.ok());

        List<?> launches = response.jsonPath().getList("content");

        assertFalse(launches.isEmpty());
    }

    @DisplayName("Create launch")
    @Test
    void shouldCreateLaunch() {
        Response response = launchesService.createLaunch(LaunchFactory.create());

        response.then().spec(ResponseSpecifications.created());

        CreateLaunchResponse created = response.as(CreateLaunchResponse.class);

        assertNotNull(created.getId());

        LaunchResponse launch = getLaunchById(created.getId());

        assertEquals(created.getId(), launch.getUuid());

        deleteLaunch(launch);
    }

    @DisplayName("Get launch by ID")
    @Test
    void shouldGetLaunchById() {
        UUID uuid = createLaunch().getId();

        Response response = launchesService.getLaunchById(uuid);

        response.then().spec(ResponseSpecifications.ok());

        LaunchResponse launch = response.as(LaunchResponse.class);

        assertEquals(uuid, launch.getUuid());

        deleteLaunch(launch);
    }

    @DisplayName("Update launch by ID")
    @Test
    void shouldUpdateLaunch() {
        UUID id = createLaunch().getId();

        LaunchResponse launch = getLaunchById(id);

        String newDescription = "Updated description";

        Response response = launchesService.updateLaunch(
                launch.getId(), new UpdateLaunchRequest(newDescription));

        response.then().spec(ResponseSpecifications.ok());

        LaunchResponse updated = getLaunchById(id);

        assertEquals(newDescription, updated.getDescription());

        deleteLaunch(updated);
    }

    @DisplayName("Delete launch by ID")
    @Test
    void shouldDeleteLaunch() {
        UUID id = createLaunch().getId();

        launchesService.finishLaunch(id, LaunchFactory.finish());

        LaunchResponse launch = getLaunchById(id);

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

    private CreateLaunchResponse createLaunch() {
        return launchesService.createLaunch(LaunchFactory.create())
                .as(CreateLaunchResponse.class);
    }

    private LaunchResponse getLaunchById(UUID id) {
        return launchesService.getLaunchById(id)
                .as(LaunchResponse.class);
    }

    private void deleteLaunch(LaunchResponse launch) {
        launchesService.finishLaunch(launch.getUuid(), LaunchFactory.finish());
        launchesService.deleteLaunch(launch.getId());
    }
}
