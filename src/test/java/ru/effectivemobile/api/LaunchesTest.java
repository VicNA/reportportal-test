package ru.effectivemobile.api;

import org.junit.jupiter.api.Test;
import ru.effectivemobile.api.services.LaunchesService;
import ru.effectivemobile.api.specs.ResponseSpecifications;

public class LaunchesTest {

    private final LaunchesService launchesService = new LaunchesService();

    @Test
    void shouldGetLaunches() {
        launchesService.getLaunches()
                .then()
                .spec(ResponseSpecifications.ok());
    }
}
