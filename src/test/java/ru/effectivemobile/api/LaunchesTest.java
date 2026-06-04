package ru.effectivemobile.api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.effectivemobile.api.data.LaunchFactory;
import ru.effectivemobile.api.models.CreateLaunchRequest;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.models.Launch;
import ru.effectivemobile.api.services.LaunchesService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LaunchesTest {

    private final LaunchesService launchesService = new LaunchesService();

//    @Test
//    void shouldGetLaunches() {
//        launchesService.getLaunches()
//                .then()
//                .spec(ResponseSpecifications.ok());
//    }

    @Test
    void launchCrudTest() {
        CreateLaunchRequest request = LaunchFactory.launch();

        Response response = launchesService.createLaunch(request);
        response.prettyPrint();

        UUID launchId = response
                .as(CreateLaunchResponse.class)
                .getId();

        Response response2 = launchesService.getLaunchById(launchId);
        response2.prettyPrint();

        Launch launch = response2
                .as(Launch.class);

        assertEquals(request.getName(), launch.getName());

        launchesService.deleteLaunch(launchId);
    }
}
