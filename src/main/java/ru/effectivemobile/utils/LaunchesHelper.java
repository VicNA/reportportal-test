package ru.effectivemobile.utils;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import ru.effectivemobile.api.data.LaunchFactory;
import ru.effectivemobile.api.models.CreateLaunchResponse;
import ru.effectivemobile.api.models.LaunchResponse;
import ru.effectivemobile.api.services.LaunchesService;

import java.util.LinkedHashMap;
import java.util.UUID;

@AllArgsConstructor
public class LaunchesHelper {

    private final LinkedHashMap<UUID, LaunchResponse> responseMap = new LinkedHashMap<>();

    private final LaunchesService launchesService;

    public CreateLaunchResponse createLaunch() {
        return launchesService.createLaunch(LaunchFactory.create())
                .as(CreateLaunchResponse.class);
    }

    public void addLaunch(CreateLaunchResponse response) {
        getLaunchById(response.getId());
    }

    public LaunchResponse getLaunchById(UUID id) {
        return asLaunchResponse(launchesService.getLaunchById(id));
    }

    public LaunchResponse asLaunchResponse(Response response) {
        LaunchResponse launchResponse = response.as(LaunchResponse.class);

        responseMap.put(launchResponse.getUuid(), launchResponse);

        return launchResponse;
    }

    public int responseSize() {
        return responseMap.size();
    }

    public LaunchResponse getFirstLaunch() {
        return responseMap.firstEntry().getValue();
    }
    public void clear() {
        responseMap.values().forEach(resp -> {
            launchesService.finishLaunch(resp.getUuid(), LaunchFactory.finish());
            launchesService.deleteLaunch(resp.getId());
        });
        responseMap.clear();
    }
}
